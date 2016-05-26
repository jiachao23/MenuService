/**
 * ��ѡ���ȫѡ����ȡ��ȫѡ����
 * 
 * @param CheckBoxPartName����ѡ���name����ǰһ���֣����������_��,�ں��������֡����硰tabledata_1�� ;
 * @param ThisCheckBoxNode��
 */
function SelectAllCheckBox(CheckBoxPartName, ThisCheckBoxNode) {
	if (ThisCheckBoxNode.checked) {// ���ѡ����ȫѡ
		var AllinputNodeList = document.getElementsByTagName("INPUT");

		for ( var i = 0; i < AllinputNodeList.length; i++) {
			if (AllinputNodeList[i].type == "checkbox"
					&& (AllinputNodeList[i].name.toString().indexOf(
							CheckBoxPartName) > -1)) {
				AllinputNodeList[i].checked = "checked";
			}
		}
	} else { // û��ȫѡȫѡ
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
 * ��ȡ��ѡ���ֵ����"##"(�����»��߸���)������1##2##3
 * 
 * @param CheckBoxPartName:��ѡ������ֵ�һ����
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
 * ��ȡ��ѡ���ֵ����"##"(�����»��߸���)������1##2##3
 * 
 * @param CheckBoxPartName:��ѡ������ֵ�һ����
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
 * ���� form �ڵ㹹�� URL
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

	// ȴ�����һ��"&"
	if (url.lastIndexOf("&") == (url.length - 1)) {
		url = url.substr(0, url.length - 1);
	}
	// ���� ����ʱ�����
	// alert("creatUrlByForm.url="+url);
	return convertURL(url);
}
// ��url��ַ����ʱ�����ƭ�������������ȡ����

function convertURL(url) {
	// ��ȡʱ���
	var timstamp = (new Date()).valueOf();
	// ��ʱ�����Ϣƴ�ӵ�url��
	// url = "AJAXServer"
	if (url.indexOf("?") >= 0) {
		url = url + "&t_yangjun=" + timstamp;
	} else {
		url = url + "?t_yangjun=" + timstamp;
	}
	return url;
}