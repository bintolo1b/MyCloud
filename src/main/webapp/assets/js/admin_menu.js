 function showSection(sectionId, element) {
        // Update active link
        document.querySelectorAll('.sidenav a').forEach(link => link.classList.remove('active'));
        element.classList.add('active');
}

document.querySelectorAll('.admin_item').forEach(function(item) {
    var url = item.getAttribute('href');
    if (url === window.location.pathname) {
        item.classList.add('active');
    }
});

let circularProgress = document.querySelector(".circular-progress"),
    progressValue = document.querySelector(".progress-value");

let progressStartValue,
    progressEndValue,
    speed = 25;

async function updateProgress() {
	progressStartValue = 0
	try {
		const response = await fetch('/PBL4/admin/getPercentCapacityUsedOfCloud');
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		const returnObject = await response.json();
		progressEndValue = returnObject.percentCloudCapacityUsed;

		document.getElementById('used-space-percent').textContent = `Cloud: ${returnObject.totalCloudCapacityUsed}GB / ${returnObject.totalCloudCapacity}GB`;
		
		let progress = setInterval(() => {
			if (Math.floor(progressStartValue) < Math.floor(progressEndValue)) {
				progressStartValue += 1;
			} else {
				progressStartValue += 0.1;
				progressStartValue = Math.min(progressStartValue, progressEndValue);
			}

			progressValue.textContent = `${progressStartValue.toFixed(1)}%`;

			circularProgress.style.background = `conic-gradient(#7d2ae8 ${progressStartValue * 3.6}deg, #ededed 0deg)`;

			if (progressStartValue >= progressEndValue) {
				clearInterval(progress);
			}
		}, speed);
	} catch (error) {
		console.log(error);
	}
}

updateProgress();	
