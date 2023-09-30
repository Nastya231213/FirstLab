package com.entity;

public class Author {
	private int authorId;

	private String fullname;
	private String pseudonym;

	public Author(String fullname, String pseudonym) {
		super();
		this.fullname = fullname;
		this.pseudonym = pseudonym;
	}

	public Author(int authorId, String fullname, String pseudonym) {
		super();
		this.authorId = authorId;
		this.fullname = fullname;
		this.pseudonym = pseudonym;
	}

	public Author() {

	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
}
