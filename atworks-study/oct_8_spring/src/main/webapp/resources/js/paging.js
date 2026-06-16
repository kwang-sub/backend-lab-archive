(function($) {
	$.fn.paging = function(options) {
		var curThis = this;
		var defaults = { 
			pageSize: 20, 
			currentPage: 1, 
			pageTotal: 0,
			pageBlock: 10
		};
		
		var subOption = $.extend(true, defaults, options);
		var goPageFnName = null;
		
		if( subOption.goPageFnName == undefined || subOption.goPageFnName == null  || subOption.goPageFnName == "" ) {
			goPageFnName = "goPage";
		} else {
			goPageFnName = subOption.goPageFnName;
		}

		return this.each(function() {
			var currentPage = subOption.currentPage*1;  // 현재 페이지
			var pageSize = subOption.pageSize*1;   // 출력 리스트 수
			var pageBlock = subOption.pageBlock*1;  // 1~10까지 블락
			var pageTotal = subOption.pageTotal*1;  // 총 데이터 수
			
			if (!pageSize) pageSize = 10;     // 출력 리스트수가 없으면 초기값 10으로 설정
			if (!pageBlock) pageBlock = 10;     // 블락 초기값이 없으면 10으로 설정
			
			var pageTotalCnt = Math.ceil(pageTotal/pageSize); //총페이지 나누기 출력 리스트 수
			var pageBlockCnt = Math.ceil(currentPage/pageBlock); //현재페이지 나누기 블락
			var sPage, ePage;
			var html = "";
			
			
			
			if (pageBlockCnt > 1) {
				sPage = (pageBlockCnt-1) * pageBlock+1;
			} else {
				sPage = 1;
			}
			
			if ((pageBlockCnt * pageBlock) >= pageTotalCnt) {
				ePage = pageTotalCnt;
			} else {
				ePage = pageBlockCnt * pageBlock;
			} 
			
			if (sPage > 1) { 
				html += '<a href="#" onclick="' + goPageFnName + '(' + 1 + ');" class="first"><span><i class="fas fa-angle-double-left"></i></span></a>';
				html += '<a href="#" onclick="' + goPageFnName + '(' + (sPage-pageBlock) + ');" class="prev"><span><i class="fas fa-angle-left"></i></span></a>'; 
			}
			
			for (var i=sPage; i<=ePage; i++) {
				if (currentPage == i) { 
					html += '<a href="#" class="active">'+i+'</a>';   
				} else {
					html += '<a href="#" onclick="' + goPageFnName + '(' + i + ');">'+i+'</a>' 
				}
			}
			
			if (ePage < pageTotalCnt) {
				html += '<a href="#" onclick="' + goPageFnName + '(' + (sPage+pageBlock) + ');" class="next"><span><i class="fas fa-angle-right"></i></span></a>'
				html += '<a href="#" onclick="' + goPageFnName + '(' + pageTotalCnt + ');" class="last"><span><i class="fas fa-angle-double-right"></i></span></a>'
			}
			
			html += '';
			
			$(curThis).empty().append(html);
		});
	};
	
	$.fn.djlPaging = function(options) {
		var curThis = this;
		var defaults = {
			pageSize: 10,
			currentPage: 1,
			pageTotal: 0,
			pageBlock: 10
		};
		
		var subOption = $.extend(true, defaults, options);
		var goPageFnName = null;
		
		if( subOption.goPageFnName == undefined || subOption.goPageFnName == null  || subOption.goPageFnName == "" ) {
			goPageFnName = "goPage";
		} else {
			goPageFnName = subOption.goPageFnName;
		}
		
		return this.each(function() {
			var currentPage = subOption.currentPage*1;  // 현재 페이지
			var pageSize = subOption.pageSize*1;   // 출력 리스트 수
			var pageBlock = subOption.pageBlock*1;  // 1~10까지 블락
			var pageTotal = subOption.pageTotal*1;  // 총 데이터 수
			
			if (!pageSize) pageSize = 10;     // 출력 리스트수가 없으면 초기값 10으로 설정
			if (!pageBlock) pageBlock = 10;     // 블락 초기값이 없으면 10으로 설정
			
			var pageTotalCnt = Math.ceil(pageTotal/pageSize);
			var pageBlockCnt = Math.ceil(currentPage/pageBlock);
			var sPage, ePage;
			var html = "";
			
			if (pageBlockCnt > 1) {
				sPage = (pageBlockCnt-1) * pageBlock+1;
			} else {
				sPage = 1;
			}
			
			if ((pageBlockCnt * pageBlock) >= pageTotalCnt) {
				ePage = pageTotalCnt;
			} else {
				ePage = pageBlockCnt * pageBlock;
			}
			
			if (sPage > 1) {
				html += '<span class="arrow pre">';
				html += '    <a href="#" onclick="' + goPageFnName + '(' + 1 + ');"><img src="/style/images/button/btn_prev2.gif" alt="처음 페이지" /></a>';
				html += '    <a href="#" onclick="' + goPageFnName + '(' + (sPage-pageBlock) + ');"><img src="/style/images/button/btn_prev.gif" alt="이전 페이지" /></a> ';
				html += '</span>';    
			}
			
			html += '<span class="list">';
			
			for (var i=sPage; i<=ePage; i++) {
				if (currentPage == i) {
					html += '<strong class="selected">' + i + '</strong> ';
				} else {
					html += '<a onclick="' + goPageFnName + '(' + i + ');">' + i + '</a> ';
				}                         
			}
			
			html += '</span>';
			
			if (ePage < pageTotalCnt) {
				html += '<span class="arrow">';
				html += '    <a href="#" onclick="' + goPageFnName + '(' + (ePage+1) + ');"><img src="/style/images/button/btn_next.gif" alt="다음 페이지" /></a>';
				html += '    <a href="#" onclick="' + goPageFnName + '(' + pageTotalCnt + ');"><img src="/style/images/button/btn_next2.gif" alt="마지막 페이지" /></a>';
				html += '</span>';
			}
			
			$(curThis).empty().append(html);
		});
	};
})(jQuery);