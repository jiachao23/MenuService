// JavaScript Document


/**
 * 褰撳湪椤电爜杈撳叆妗嗕腑鎸変簡鍥炶溅鍚庯紝灏变細鎻愪氦鍒嗛〉琛ㄥ崟
 */
function submitPaginationFormByInput( event, pageNum ){
	if( event.keyCode == 13 ){
		gotoPage(pageNum);
		return false;
	}
}


/**
 * 椤甸潰涓渶瑕佹湁涓�釜鎻愪氦鍒嗛〉瑕佹眰鐨勮〃鍗曞厓绱狅紝濡傛灉鏈夊涓猣orm锛屽垯闇�鎸囧畾id涓簆aginationForm銆�
 * 浼犻�鐨勫弬鏁颁腑鏈変竴涓〃绀鸿鏌ョ湅鐨勯〉鐮佺殑锛屽悕绉颁负"pageNum"銆�
 *
 * 闇�鐢卞叿浣撶殑椤甸潰瀹氫箟 pageCount 鍙橀噺锛岀敤浜庢寚瀹氭�椤电爜鏈夊灏戯紒锛侊紒
 * 
 * @param pageNum 瑕佽烦杞埌鐨勯〉鐮�
 * @param pageCountParam 鎬婚〉鐮�
 */
function gotoPage( pageNum ){
	if( typeof(pageCount) == "undefined" ){
		alert("璇锋寚瀹氭�椤电爜鏁帮紙瀹氫箟鍙橀噺pageCount锛夛紒");
		return;
	}	
	
	// 鎵惧嚭杈撳叆椤甸潰鐨勬枃鏈
	var pageNumInput = document.getElementById("PageSelector_PageNumInput");

	// 榛樿鎵緄d涓簆aginationForm鐨�form>鍏冪礌锛屼竴涓〉闈腑鏈夊<form>涓旇繖涓〃鍗曚笉鏄1涓椂浣跨敤銆�
	// 濡傛灉鎵句笉鍒版寚瀹歩d鐨�form>锛屽氨浣跨敤椤甸潰涓殑绗�涓�form>锛屽綋椤甸潰涓彧鏈変竴涓垨鍒嗛〉琛ㄥ崟鏄1涓椂杩欐牱浣跨敤锛岀敤璧锋潵鏂逛究銆�
	var paginationForm = document.getElementById("paginationForm");
	if( paginationForm == null ){
		paginationForm = document.forms[0];
	}
	if( paginationForm == null ){
		alert("鎵句笉鍒板垎椤电敤鐨勮〃鍗曪紒");
		return;
	}
	
	// 瑙ｅ喅灏忔暟鐐圭殑闂锛堝幓鎺夊皬鏁扮偣鍚庣殑鏁板瓧锛�
	pageNum = parseInt(pageNum);

	// 濡傛灉涓嶆槸鏁板瓧锛屽氨鎻愮ず閿欒
	if( pageNum == "" || isNaN(pageNum) ){
		alert("璇峰～鍐欐纭殑椤电爜鏁板瓧锛佽寖鍥存槸 1-" + pageCount);
		if( pageNumInput != null ){
			pageNumInput.focus();
			pageNumInput.select();
		}
		return;
	}
		
	// 濡傛灉鏁板瓧瓒呭嚭椤电爜鑼冨洿锛屽氨鎻愮ず閿欒
	if( pageNum < 1 || pageNum > pageCount ){
		alert("璇峰～鍐欐纭殑椤电爜锛佽寖鍥存槸 1-" + pageCount);
		if( pageNumInput != null ){
			pageNumInput.focus();
			pageNumInput.select();
		}
		return;
	}
	
	// 濡傛灉鎵句笉鍒板悕涓�pageNum"鐨勫瓧娈碉紝灏卞鍔犱竴涓紝鐢ㄤ簬浼犻�椤电爜鍙傛暟銆�
	if(	paginationForm.pageNum == null ){
		var pageNumInput = document.createElement("<input type='hidden' name='pageNum' value='1'/>");
		paginationForm.appendChild(pageNumInput);
	}
	
	// 澶勭悊pageSize
	var pageSize =  $("#PaginationBar_PageSizeSelector").val();	
	
	// 濡傛灉鎵句笉鍒板悕涓�pageSize"鐨勫瓧娈碉紝灏卞鍔犱竴涓�
	if(	paginationForm.pageSize == null ){
		var pageSizeField= document.createElement("<input type='hidden' name='pageSize' value='" + pageSize + "'/>");
		paginationForm.appendChild(pageSizeField);
		//alert("new ");
	}else{
		$(paginationForm.pageSize).val( pageSize );
		//alert("old ");
	}
	
	//alert(pageSize);
	//alert( $(paginationForm.pageSize).val() );
	
	// 淇敼椤电爜鍊煎悗鎻愪氦琛ㄥ崟
	paginationForm.pageNum.value = pageNum;
	paginationForm.submit();
}


/**
 * 鍒犻櫎鍓嶇殑纭鎻愮ず
 */
function delConfirm(){
	return window.confirm("是否删除？");	
}



/**
 * 锛堜娇鐢ㄦā寮忓娲绘锛夋樉绀哄浘鐗�
 */ 
function showImage( url, width, height ){
	var sFeatures = "resizable: yes; ";
	if( width != null ){
		sFeatures += "dialogWidth:" + width + "px; ";
	}
	if( height != null ){
		sFeatures += "dialogHeight:" + height + "px; ";
	}
	window.showModalDialog(url, null, sFeatures);
}

