package model.dao;

import java.util.ArrayList;

public interface DAOInterface<T> {	
	public void Insert(T obj);
	public void Update(T obj);
	public void Delete(T obj);
	public ArrayList<T> getAll();
	public ArrayList<T> selectByCondition(String condition, Object... params);
}
