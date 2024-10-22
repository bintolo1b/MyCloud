function assignPDFImgToImgTag(folderPath, fileName, imgTagId){
	console.log(folderPath);
	/*console.log(fileName);
	console.log(imgTagId);
	console.log("_______qwe")*/
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
				imgTag.src = returnObject.demoImgURL
			}
			else{
				imgTag.src = '/PBL4/assets/img/file.png'
			}
			
		})
		.catch(function(error){
			console.log(error);
		})
}