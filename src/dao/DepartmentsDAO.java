package dao;

import data.Department;

import java.util.Collection;

public interface DepartmentsDAO {
    int insert(Department department);

    boolean delete(Department department);

    Department findByID(int id);

    boolean update(Department department);

    boolean saveOrUpdate(Department department);

    Collection<Department> findByName(String name);
}
