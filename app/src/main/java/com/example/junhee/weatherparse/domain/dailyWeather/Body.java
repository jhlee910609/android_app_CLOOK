package com.example.junhee.weatherparse.domain.dailyWeather;

/**
 * Created by JunHee on 2017. 7. 11..
 */

public class Body {

    private String totalCount;

    private Items items;

    private String pageNo;

    private String numOfRows;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    @Override
    public String toString() {
        return "ClassPojo [totalCount = " + totalCount + ", items = " + items + ", pageNo = " + pageNo + ", numOfRows = " + numOfRows + "]";
    }
}