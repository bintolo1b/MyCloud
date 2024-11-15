const currentUrl = window.location.pathname;

const navLinks = document.querySelectorAll('.userHomePageItem a');

const newButton = document.querySelector('a.waves-effect.waves-light.btn.btn-flat.white-text');
const Search = document.querySelector('input[type="search"]')


navLinks.forEach(link => {
    if (link.getAttribute('href') === currentUrl) {
		if (currentUrl !== '/PBL4/userhomepage/main') {
			Search.disabled = true;
			document.querySelectorAll('.material-icons').forEach(element => {
		    element.onmouseover = () => element.style.cursor = 'default'; // or any desired cursor style
		    element.onmouseout = () => element.style.cursor = '';
			});
			newButton.className = '';
		}
        const icon = link.querySelector('i');
        if (icon) {
            icon.classList.add('blue-text', 'text-darken-1');
        }

        link.parentElement.classList.add('active');
    }
});

document.addEventListener('click', function(event) {
    const notifyIcon = document.querySelector('.notify');
    const notifyBlock = document.querySelector('.notify-block');

    if (!notifyIcon.contains(event.target) && !notifyBlock.contains(event.target)) {
        notifyBlock.classList.remove('open'); 
    }
});

document.querySelector('.notify').addEventListener('click', function(e) {
    const notifyBlock = document.querySelector('.notify-block'); 

    notifyBlock.classList.toggle('open');
    
    e.stopPropagation();
});

let circularProgress = document.querySelector(".circular-progress"),
    progressValue = document.querySelector(".progress-value");

let progressStartValue = 0,
    progressEndValue,
    speed = 25;

async function updateProgress() {
	try {
		const response = await fetch('/PBL4/getPercentSpaceUsed');
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		const returnObject = await response.json();
		progressEndValue = returnObject.percentSpaceUsed;

		document.getElementById('used-space-percent').textContent = `Used: ${returnObject.totalSizeUsed}GB / ${returnObject.totalSize}GB`;
		
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


    

