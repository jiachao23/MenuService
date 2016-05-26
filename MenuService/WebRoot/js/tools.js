/**
 * 复选框的全选（或取消全选），
 * 
 * @param CheckBoxPartName，复选框的name的向前一部分，后面紧跟“_”,在后面是数字。例如“tabledata_1” ;
 * @param ThisCheckBoxNode，
 */
function SelectAllCheckBox(CheckBoxPartName, ThisCheckBoxNode) {
	if (ThisCheckBoxNode.checked) {// 如果选择了全选
		var AllinputNodeList = document.getElementsByTagName("INPUT");

		for ( var i = 0; i < AllinputNodeList.length; i++) {
			if (AllinputNodeList[i].type == "checkbox"
					&& (AllinputNodeList[i].name.toString().indexOf(
							CheckBoxPartName) > -1)) {
				AllinputNodeList[i].checked = "checked";
			}
		}
	} else { // 没有全选全选
		var AllinputNodeList = document.getElementsByTagName("INPUT");
		for ( var i = 0; i < AllinputNodeList.length; i++) {
			if (AllinputNodeList[i].type == "checkbox"
					&& (AllinputNodeList[i].name.toString().indexOf(
							CheckBoxPartName) > -1)) {
				AllinputNodeList[i].checked = "";
			}
		}

	}
}

/**
 * 获取复选框的值，用"##"(三个下划线隔开)，例如1##2##3
 * 
 * @param CheckBoxPartName:复选框的名字的一部分
 */
function getCheckBoxsValue(CheckBoxPartName) {
	var values = "";
	var AllinputNodeList = document.getElementsByTagName("INPUT");
	for ( var i = 0; i < AllinputNodeList.length; i++) {
		if (AllinputNodeList[i].type == "checkbox")
			if ((AllinputNodeList[i].name.toString().indexOf(CheckBoxPartName) > -1))
				if (AllinputNodeList[i].checked)
					if (AllinputNodeList[i].value != "on")
						// alert(AllinputNodeList[i].value);
						values += (AllinputNodeList[i].value + "##");

	}

	if (values != "") {
		values = values.substring(0, values.length - 2);
	}
	// alert(values);
	return values;
}

/**
 * 获取复选框的值，用"##"(三个下划线隔开)，例如1##2##3
 * 
 * @param CheckBoxPartName:复选框的名字的一部分
 */
function getCheckBoxsNames(CheckBoxPartName) {
	var values = "";
	var AllinputNodeList = document.getElementsByTagName("INPUT");
	for ( var i = 0; i < AllinputNodeList.length; i++) {
		if (AllinputNodeList[i].type == "checkbox")
			if ((AllinputNodeList[i].name.toString().indexOf(CheckBoxPartName) > -1))
				if (AllinputNodeList[i].checked)
					if (AllinputNodeList[i].value != "on")
						// alert(AllinputNodeList[i].value);
						values += (AllinputNodeList[i].name + "##");

	}

	if (values != "") {
		values = values.substring(0, values.length - 2);
	}
	// alert(values);
	return values;
}


/**
 * 根据 form 节点构造 URL
 * 
 * @param formNode
 */
function creatUrlByForm(formNode) {

	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, '');
	}
	var url = formNode.action + "?";
	var i = 0;
	for (i = 0;; i++) {
		if (formNode.elements[i]) {
			var temptype = "";
			try {
				temptype = formNode.elements[i].type.toUpperCase();
			} catch (e) {
				temptype = "";
			}
			var tempvalue = null;
			if (temptype == "radio".toUpperCase()
					|| temptype == "checkbox".toUpperCase()) {
				if (formNode.elements[i].checked) {
					tempvalue = formNode.elements[i].value;
					if (tempvalue != null && tempvalue != ""
							&& tempvalue.length != 0)
						url += formNode.elements[i].name + "="
								+ encodeURI(tempvalue.toString().trim()) + "&";
					// url += formNode.elements[i].name + "="
					// +encodeURI(encodeURI(tempvalue))+"&";
					// url += formNode.elements[i].name + "="
					// +encodeURI(encodeURI(encodeURI(tempvalue)))+"&";
					// url += formNode.elements[i].name + "=" +tempvalue+"&";
				}
			} else {
				tempvalue = formNode.elements[i].value;
				//if (tempvalue != null && tempvalue != ""&& tempvalue.length != 0)
					// url += formNode.elements[i].name + "="
					// +encodeURI(encodeURI(tempvalue)) +"&";
					// url += formNode.elements[i].name + "="
					// +encodeURI(encodeURI(encodeURI(tempvalue))) +"&";
					url += formNode.elements[i].name + "="
							+ encodeURI(tempvalue.toString().trim()) + "&";
				// url += formNode.elements[i].name + "=" + tempvalue +"&";
			}

		} else {
			break;
		}
	}

	// 却掉最后一个"&"
	if (url.lastIndexOf("&") == (url.length - 1)) {
		url = url.substr(0, url.length - 1);
	}
	// 最后给 加上时间戳，
	// alert("creatUrlByForm.url="+url);
	return convertURL(url);
}
// 给url地址增加时间戳，骗过浏览器，不读取缓存

function convertURL(url) {
	// 获取时间戳
	var timstamp = (new Date()).valueOf();
	// 将时间戳信息拼接到url上
	// url = "AJAXServer"
	if (url.indexOf("?") >= 0) {
		url = url + "&t_yangjun=" + timstamp;
	} else {
		url = url + "?t_yangjun=" + timstamp;
	}
	return url;
}