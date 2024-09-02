package com.example.demo.model;

public class PageDto {
	
	private int startPage;
	private int endPage;
	private int total;
	private int cntPerPage;
	private int lastPage;
	private int start;
	private int end;
	private int nowPage;
	private int cntPage = 10;
	
	
	
	
	public PageDto () {}
	
	public PageDto(int total,int nowPage, int cntPerPage) {
		if(nowPage < 1) {
			nowPage = 1;
		}
		
		
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		clacLastPage(getTotal(), getCntPerPage());
		
		if (nowPage > getLastPage()) {
	        nowPage = getLastPage();
	    }
		setNowPage(nowPage);
		
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
	}
	
	//제일 마지막 페이지 계산식
	// ex) (ceil)현재 페이지 76 / 페이지당 글 갯수 (10) -> 7.6 -> 8 
	// 마지막페이지는 8
	public void clacLastPage(int total,int cntPerPage ) {
		setLastPage((int)Math.ceil((double)total / (double)cntPerPage));
	}
	
	// 시작, 끝 페이지 계산 
	// 끝페이지 = 현재페이지(5) / 10 = 0.5 -> 1 에서 * 10 하면 10 .에서 EndPage가 10이지만,LastPage가 8이므로, EndPage는 8.
	// 시작페이지 = 끝페이지(8) - 10 +1 = -1 -> 1페이지.
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		
		setStartPage(getEndPage() - cntPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
		
	}
	// db 쿼리에서 사용할 start, end 
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
	
	}
	
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}


	public int getCntPage() {
		return cntPage;
	}

	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}

	
	@Override
	public String toString() {
		return "PageDto [startPage=" + startPage + ", endPage=" + endPage + ", total=" + total + ", cntPerPage="
				+ cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end + ", nowPage=" + nowPage
				+ ", cntPage=" + cntPage + "]";
	}


	
	
}
