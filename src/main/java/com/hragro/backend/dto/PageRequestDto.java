package com.hragro.backend.dto;

public class PageRequestDto {
    private int page = 0;
    private int size = 10;
    private String sortBy = "createdAt";
    private String sortDirection = "desc";
    private String search = "";
    private String category = "";

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    
    public String getSortDirection() { return sortDirection; }
    public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
    
    public String getSearch() { return search; }
    public void setSearch(String search) { this.search = search; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}