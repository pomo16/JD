package com.ssm.common.entity;

public class BaseEntity{
	private Integer id;
	private int[] ids;
	private int rows; // ÿҳ��¼����
	private int page; // ��ҳҳ��

	public BaseEntity() {
		super();
	}
	
	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
