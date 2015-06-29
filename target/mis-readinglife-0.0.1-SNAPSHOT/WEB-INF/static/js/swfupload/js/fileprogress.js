/*
		[Leo.C, Studio] (C)2004 - 2008
		
   		$Hanization: LeoChung $
   		$E-Mail: who@imll.net $
   		$HomePage: http://imll.net $
   		$Date: 2008/11/8 18:02 $
*/
/*
	A simple class for displaying file information and progress
	Note: This is a demonstration only and not part of SWFUpload.
	Note: Some have had problems adapting this class in IE7. It may not be suitable for your application.
*/

// Constructor
// file is a SWFUpload file object
// targetID is the HTML element id attribute that the FileProgress HTML structure will be added to.
// Instantiating a new FileProgress object with an existing file will reuse/update the existing DOM elements
function FileProgress(file, targetID) {
	this.fileProgressID = file.id;

	this.opacity = 100;
	this.height = 0;
	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "progress-tr";
		this.fileProgressWrapper.id = this.fileProgressID;

		this.fileProgressElement = document.createElement("div");
		this.fileProgressElement.className = "progressContainer";


		var uploadFileName = document.createElement("div");
		uploadFileName.className = "uploadFileName";
		uploadFileName.appendChild(document.createTextNode(file.name));
		
		var uploadFileSize = document.createElement("div");
		uploadFileSize.className = "uploadFileSize";
		var size = file.size; 
		var unit = "B";
		if(size>(1024*1024*1024)){
			unit="GB";
			size /=(1024*1024*1024);
		}else if(size>(1024*1021)){
			unit="MB";
			size /=(1024*1024);
		}else if(size>1024){
			unit="KB";
			size /=1024;
		}
		var fsize=size.toFixed(2)+" "+unit;
		uploadFileSize.appendChild(document.createTextNode(fsize));

		

		var uploadProgress = document.createElement("div");
		uploadProgress.className = "uploadProgress";
		var progressContainer=document.createElement("div");
		progressContainer.className = "progressContainerInProgress";
		var progressPercent=document.createElement("div");
		progressPercent.className = "progressBarInProgress";
		progressContainer.appendChild(progressPercent);
		uploadProgress.appendChild(progressContainer);

		var uploadStatus = document.createElement("div");
		uploadStatus.className = "uploadStatus";
		uploadStatus.innerHTML = "&nbsp;";
		
		var uploadOperate = document.createElement("div");
		uploadOperate.className = "uploadOperate";
		var cancelButton = document.createElement("a");
		cancelButton.className = "progressCancel";
		cancelButton.href = "#";
		cancelButton.style.visibility = "hidden";
		cancelButton.appendChild(document.createTextNode(" "));
		uploadOperate.appendChild(cancelButton);
		
		this.fileProgressElement.appendChild(uploadFileName);
		this.fileProgressElement.appendChild(uploadFileSize);
		this.fileProgressElement.appendChild(uploadProgress);
		this.fileProgressElement.appendChild(uploadStatus);
		this.fileProgressElement.appendChild(uploadOperate);

		this.fileProgressWrapper.appendChild(this.fileProgressElement);

		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
	} else {
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
	}

	this.height = this.fileProgressWrapper.offsetHeight;

}
FileProgress.prototype.setProgress = function (percentage) {
	this.fileProgressElement.className = "progressContainer green";
	this.fileProgressElement.childNodes[2].childNodes[0].className = "progressContainerInProgress";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].className = "progressBarInProgress";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].style.width = percentage + "%";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].innerHTML=percentage + "%";
};
FileProgress.prototype.setComplete = function () {
	this.fileProgressElement.className = "progressContainer blue";
	this.fileProgressElement.childNodes[2].childNodes[0].className = "progressContainerComplete";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].className = "progressBarComplete";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].style.width = "100%";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].innerHTML= "100%";
	/**
	var oSelf = this;
	setTimeout(function () {
		oSelf.disappear();
	}, 10000);
	**/
};
FileProgress.prototype.setError = function () {
	this.fileProgressElement.className = "progressContainer red";
	this.fileProgressElement.childNodes[2].childNodes[0].className = "progressContainerError";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].className = "progressBarError";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].style.width = "";
	//this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].innerHTML= "0%";
	/**
	var oSelf = this;
	setTimeout(function () {
		oSelf.disappear();
	}, 5000);
	**/
};
FileProgress.prototype.setCancelled = function () {
	
	this.fileProgressElement.className = "progressContainer";
	this.fileProgressElement.childNodes[2].childNodes[0].className = "progressContainerError";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].className = "progressBarError";
	this.fileProgressElement.childNodes[2].childNodes[0].childNodes[0].style.width = "";
	
	var oSelf = this;
	setTimeout(function () {
		oSelf.disappear();
	}, 2000);
	
};
FileProgress.prototype.setStatus = function (status) {
	this.fileProgressElement.childNodes[3].innerHTML = status;
};

// Show/Hide the cancel button
FileProgress.prototype.toggleCancel = function (show, swfUploadInstance) {
	this.fileProgressElement.childNodes[4].childNodes[0].style.visibility = show ? "visible" : "hidden";
	if (swfUploadInstance) {
		var fileID = this.fileProgressID;
		this.fileProgressElement.childNodes[4].childNodes[0].onclick = function () {
			swfUploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};

// Fades out and clips away the FileProgress box.
FileProgress.prototype.disappear = function () {

	var reduceOpacityBy = 15;
	var reduceHeightBy = 4;
	var rate = 30;	// 15 fps

	if (this.opacity > 0) {
		this.opacity -= reduceOpacityBy;
		if (this.opacity < 0) {
			this.opacity = 0;
		}

		if (this.fileProgressWrapper.filters) {
			try {
				this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = this.opacity;
			} catch (e) {
				// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
				this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + this.opacity + ")";
			}
		} else {
			this.fileProgressWrapper.style.opacity = this.opacity / 100;
		}
	}

	if (this.height > 0) {
		this.height -= reduceHeightBy;
		if (this.height < 0) {
			this.height = 0;
		}

		this.fileProgressWrapper.style.height = this.height + "px";
	}

	if (this.height > 0 || this.opacity > 0) {
		var oSelf = this;
		setTimeout(function () {
			oSelf.disappear();
		}, rate);
	} else {
		this.fileProgressWrapper.style.display = "none";
	}
};