package dynamic.dao;

import java.util.HashMap;
import java.util.List;

import dto.Emp;

public interface DynamicQueryDao {

	public List<HashMap<String, Object>> select1();

	public List<HashMap<String, Object>> select2(String data);

	public List<HashMap<String, Object>> select3(Emp emp);

	public List<HashMap<String, Object>> select4(HashMap<String, Object> param);

	public HashMap<String, Object> selectByEmpno(int i);

	public void update(HashMap<String, Object> param);

	public List<HashMap<String, Object>> selectCheckbox(HashMap<String, Object> param);

}
