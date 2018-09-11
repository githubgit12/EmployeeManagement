package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

//�ļ����ݵ�����Ͷ�ȡ
public class ScoreDao extends BaseDao {
	// Connection conn;
	// Statement stat;
	// PreparedStatement pstat;
	// ResultSet rs;
	// 1.����jar��

	/*
	 * ���ݵĲ�ѯ
	 */

	public List<Score> search() {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		// 2.���÷��䣬�������ݿ�����
		try {
			conn = getConntion();
			stat = conn.createStatement();
			// 5.����sql����ѯ
			rs = stat.executeQuery("select * from v_score");
			// 6.��������
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("id"));
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				sc.setEmp(emp);
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setGrade(rs.getString("grade"));
				list.add(sc);
			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Score> searchDep() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Score> list = new ArrayList<>();
		// 2.���÷��䣬�������ݿ�����
		try {
			conn = getConntion();
			stat = conn.createStatement();
			// 5.����sql����ѯ
			rs = stat.executeQuery("select distinct d_name from v_score");
			// 6.��������
			while (rs.next()) {
				Score sc = new Score();
				Employee emp = new Employee();
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				sc.setEmp(emp);
				list.add(sc);
			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Score> searchPro() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Score> list = new ArrayList<>();
		// 2.���÷��䣬�������ݿ�����
		try {
			conn = getConntion();
			stat = conn.createStatement();
			// 5.����sql����ѯ
			rs = stat.executeQuery("select distinct p_name from v_score  where p_name is not null");
			// 6.��������
			while (rs.next()) {
				Score sc = new Score();
				Project pro = new Project();
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				list.add(sc);
			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Score> searchGrade() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Score> list = new ArrayList<>();
		// 2.���÷��䣬�������ݿ�����
		try {
			conn = getConntion();
			stat = conn.createStatement();
			// 5.����sql����ѯ
			rs = stat.executeQuery("select distinct grade from v_score  where grade is not null");
			// 6.��������
			while (rs.next()) {
				Score sc = new Score();
				sc.setGrade(rs.getString("grade"));
				list.add(sc);
			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Score> searchByCondition(Score condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Score> list = new ArrayList<>();
		// 2.���÷��䣬�������ݿ�����

		try {

			conn = getConntion();
			stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getEmp().getName() != null && !condition.getEmp().getName().equals("")) {
				where += " and e_name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getEmp().getDep().getName() != null && !condition.getEmp().getDep().getName().equals("")) {
				where += " and d_name='" + condition.getEmp().getDep().getName() + "'";
			}
			if (condition.getPro().getName() != null && !condition.getPro().getName().equals("")) {
				where += " and p_name='" + condition.getPro().getName() + "'";
			}
			if (condition.getValue() != -1) {
				where += " and value=" + condition.getValue() + "";
			}
			if (condition.getGrade() != null && !condition.getGrade().equals("")) {
				where += " and grade= '" + condition.getGrade() + "'";
			}

			// 5.����sql����ѯ
			rs = stat.executeQuery("select * from v_score " + where);
			// 6.��������
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("id"));
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				sc.setEmp(emp);
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				sc.setValue(rs.getInt("value"));
				sc.setGrade(rs.getString("grade"));
				list.add(sc);
			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Score> searchByCondition(Score condition, int begin, int size) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Score> list = new ArrayList<>();
		// 2.���÷��䣬�������ݿ�����

		try {

			conn = getConntion();
			stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getEmp().getName() != null && !condition.getEmp().getName().equals("")) {
				where += " and e_name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getEmp().getDep().getName() != null && !condition.getEmp().getDep().getName().equals("")) {
				where += " and d_name='" + condition.getEmp().getDep().getName() + "'";
			}
			if (condition.getPro().getName() != null && !condition.getPro().getName().equals("")) {
				where += " and p_name='" + condition.getPro().getName() + "'";
			}
			if (condition.getValue() != -1) {
				where += " and value=" + condition.getValue() + "";
			}
			if (condition.getGrade() != null && !condition.getGrade().equals("")) {
				where += " and grade= '" + condition.getGrade() + "'";
			}

			// 5.����sql����ѯ
			rs = stat.executeQuery("select * from v_score " + where + " limit " + begin + "," + size);
			// 6.��������
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("id"));
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				sc.setEmp(emp);
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setGrade(rs.getString("grade"));
				list.add(sc);
			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ���ݵ����
	 */
	public boolean add(Score sc) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;

		try {
			conn = getConntion();
			// 4.����Statement
			stat = conn.createStatement();
			// 5.ִ��sql��䲢�õ����
			int rs = stat.executeUpdate("insert into score(name) values('" + sc.getValue() + "')");

			// 6.�Խ�������д���
			if (rs > 0) {
				flag = true;
			}
			// 7.�ر�
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean update(Score sc) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;

		int rs = 0;
		try {
			conn = getConntion();
			// 4.����Statement
			stat = conn.createStatement();
			// 5.ִ��sql��䲢�õ����

			String sql = "update score set value=" + sc.getValue() + " where id =" + sc.getId() + "";
			// System.out.println(sql);
			rs = stat.executeUpdate(sql);

			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs > 0;
	}

	public int addValue(Score sc) {
		Connection conn = null;
		Statement stat = null;

		int rs = 0;
		int id = 0;
		boolean flag = false;
		try {
			conn = getConntion();
			// 4.����Statement
			stat = conn.createStatement();
			// 5.ִ��sql��䲢�õ����
			String sql = "insert into score(e_id,p_id,value) values(" + sc.getEmp().getId() + "," + sc.getPro().getId()
					+ "," + sc.getValue() + ")";
			rs = stat.executeUpdate(sql);

			sql = "select last_insert_id()";
			ResultSet r = stat.executeQuery(sql);
			if (r.next()) {
				id = r.getInt(1);
			}

			// 7.�ر�
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public boolean save(List<Score> list) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;

		try {
			conn = getConntion();
			stat = conn.createStatement();
			for (int i = 0; i < list.size(); i++) {
				String sql = "update score set value=" + list.get(i).getValue() + " where e_id ="
						+ list.get(i).getE_id() + "";
				int rs = stat.executeUpdate(sql);
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean save1(int value, int e_id) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;

		try {
			conn = getConntion();
			stat = conn.createStatement();

			String sql = "update score set value=" + value + " where e_id =" + e_id + "";
			int rs = stat.executeUpdate(sql);
			if (rs > 0) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Score search(int id) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Score sc = new Score();
		// 2.���÷��䣬�������ݿ�����
		try {
			conn = getConntion();
			stat = conn.createStatement();
			// 5.����sql����ѯ
			rs = stat.executeQuery("select * from v_score where id=" + id);
			// 6.��������
			while (rs.next()) {

				sc.setId(rs.getInt("id"));
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				sc.setEmp(emp);
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setGrade(rs.getString("grade"));

			}
			// 7.�ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sc;
	}

	public List<Integer> searchProByDepId(int depId) {
		List<Integer> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConntion();
			stat = conn.createStatement();
			String sql = "select p_id from r_dep_pro where d_id=" + depId;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
