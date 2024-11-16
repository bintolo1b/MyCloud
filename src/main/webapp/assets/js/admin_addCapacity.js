async function getCapacityInfo(username) {
    try {
        const response = await fetch("/PBL4/admin/getPercentCapacityUsedByUsername?username=" + username);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

async function showUserDetails() {
    const usernameInput = document.getElementById('storage-username').value;
    if (usernameInput !== "") {
        try{
            const response = await fetch('/PBL4/getnamebyusername?username=' + usernameInput);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const returnObject = await response.json();
        
            if (returnObject.message !== "Username not found!"){
                document.getElementById('user-fullname').value = returnObject.message;
                document.getElementById('storage-inputs').classList.remove('hidden');

                const capacityData = await getCapacityInfo(usernameInput);
                if (!capacityData.erorrMessage){
                    document.getElementById('storage-capacity').value = capacityData.totalCapacityUsed + " GB / " + capacityData.totalCapacity + " GB" +" (" + capacityData.percentCapacityUsed + "%)";
                }
            }
            else{
                alert(returnObject.message);
                document.getElementById('user-fullname').value = "";
                document.getElementById('storage-inputs').classList.add('hidden');
            }
        }
        catch(error){
            console.error(error);
        }  
    } 
}

document.querySelector('.add-storage-form').addEventListener('submit', async function(event) {
    event.preventDefault();
    const username = document.getElementById('storage-username').value;
    const addedCapacity = document.getElementById('additional-storage').value;

    const data = {
        username: username,
        addedCapacity: addedCapacity
    };

    try {
        const response = await fetch("/PBL4/admin/addCapacity", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const returnObject = await response.json();
        alert(returnObject.message);

        if (returnObject.message.includes("successfully")) {
            updateProgress();
            const capacityData = await getCapacityInfo(username);
            if (!capacityData.errorMessage) {
                document.getElementById('storage-capacity').value = capacityData.totalCapacityUsed + " GB / " + capacityData.totalCapacity + " GB" + " (" + capacityData.percentCapacityUsed + "%)";
            }
        }
    } catch (error) {
        console.log(error);
    }
});
