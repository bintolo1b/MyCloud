function checkFileExists(url) {
    return fetch(url, {method: 'HEAD'})
        .then(function(response) {
            return response.ok;
        })
        .catch(function(error) {
            console.error('Error ocur:', error);
            return true;  //return true for stopping if server die or internet stop
        });
}


function assignPDFImgToImgTag(folderPath, fileName, imgTagId){
	var imgTag = document.getElementById(imgTagId);
	var folderPathEnc = encodeURIComponent(folderPath);
	var fileNameEnc = encodeURIComponent(fileName);

	var url = `http://localhost:8080/PBL4/gettemporarydemoimgurl?folderPath=${folderPathEnc}&fileName=${fileNameEnc}`;

	fetch(url) 
		.then(function(response){
			if (!response.ok) {
           	 	throw new Error('Network response was not ok');
       		}
			else
				return response.json();
		})
		.then(function(returnObject){
			if (returnObject.demoImgURL){
				//imgTag.classList.add('loading'); css class loading vao day
				const checkImgPath = returnObject.demoImgURL;
                const intervalId = setInterval(() => {
                    checkFileExists(checkImgPath)
                    	.then(exists => {
		                        if (exists) {
									//imgTag.classList.remove('loading') //xoa class loading
									console.clear();
		                            imgTag.src = checkImgPath;
		                            clearInterval(intervalId);
		                        }
                    	});
                    }, 500);
			}
			else{
				imgTag.src = '/PBL4/assets/img/file.png'
			}
			
		})
		.catch(function(error){
			console.log(error);
		})
}