package com.ssm.common.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int[] ids;
	private int rows; // 每页记录条数
	private int page; // 分页页码
	private int pageStart; // 每页起始记录下标

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
		this.pageStart = (page - 1) * rows; // 设置每页起始记录下标
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
}
