package com.dao;

public interface DatabaseDAO<T> {

	void create(T entity);

	T find(int id);

	void update(T entity);

	void delete(int id);
}