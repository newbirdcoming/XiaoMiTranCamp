### a、建表sql语句

```
/**
*创建部门表
*/
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    name VARCHAR(100) NOT NULL COMMENT '部门名称',
    parent_id INT DEFAULT NULL COMMENT '上级部门ID',
    leader_id INT DEFAULT NULL COMMENT '部门负责人ID',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态(1-正常,0-停用)',
    description VARCHAR(255) DEFAULT NULL COMMENT '部门描述',
    gmt_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 

/**
*创建员工表
*/
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '员工ID',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    gender TINYINT NOT NULL DEFAULT 0 COMMENT '性别(0-女,1-男)',
    phone VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    email VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
    birth_date DATE DEFAULT NULL COMMENT '出生日期',
    hire_date DATE NOT NULL COMMENT '入职日期',
    manager_id INT DEFAULT NULL COMMENT '直属上级ID',
    position TINYINT NOT NULL COMMENT '职位(1-普通员工,2-主管,3-经理,4-总监,5-副总裁,6-总裁)',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态(1-在职,0-离职,2-休假)',
    gmt_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 


/**
*创建项目表
*/
CREATE TABLE project (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '项目ID',
    name VARCHAR(100) NOT NULL COMMENT '项目名称',
    leader_id INT NOT NULL COMMENT '项目负责人ID',
    start_date DATE NOT NULL COMMENT '开始日期',
    end_date DATE DEFAULT NULL COMMENT '结束日期',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态(1-进行中,0-已结束,2-暂停)',
    description VARCHAR(255) DEFAULT NULL COMMENT '项目描述',
    gmt_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 

/**
*创建员工部门关系表
*/
CREATE TABLE emp_dep_relation (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '关系ID',
    employee_id INT NOT NULL COMMENT '员工ID',
    department_id INT NOT NULL COMMENT '部门ID',
    start_date DATE NOT NULL COMMENT '开始日期',
    end_date DATE DEFAULT NULL COMMENT '结束日期',
    is_primary TINYINT NOT NULL DEFAULT 0 COMMENT '是否主部门(1-是,0-否)',
    gmt_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    UNIQUE KEY unique_emp_dep (employee_id, department_id, start_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 

/**
*创建员工项目关系表
*/
CREATE TABLE emp_pro_relation (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '关系ID',
    employee_id INT NOT NULL COMMENT '员工ID',
    project_id INT NOT NULL COMMENT '项目ID',
    role TINYINT NOT NULL DEFAULT 1 COMMENT '角色(1-成员,2-负责人,3-观察者)',
    start_date DATE NOT NULL COMMENT '开始日期',
    end_date DATE DEFAULT NULL COMMENT '结束日期',
    gmt_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    UNIQUE KEY unique_emp_pro (employee_id, project_id, start_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4   
```





b、查询员工张三的上级领导姓名

```
SELECT e2.name
FROM employee e1
JOIN employee e2 ON e1.manager_id = e2.id
WHERE e1.name = '张三'
```



c、统计财务部的员工数

```
SELECT COUNT(edr.employee_id) AS employee_count
FROM department d
JOIN emp_dep_relation edr ON d.ID = edr.department_id
WHERE d.name = '财务部';
```



d统计财务部门及其子部门的员工数

（递归查询）

```
WITH RECURSIVE DepartmentCTE AS (
    SELECT ID, name, parent_id
    FROM department
    WHERE name = '财务部'
    UNION ALL
    SELECT d.ID, d.name, d.parent_id
    FROM department d
    JOIN DepartmentCTE cte ON d.parent_id = cte.ID
)
SELECT COUNT(edr.employ_id) AS employee_count
FROM DepartmentCTE dcte
JOIN emp_dep_relation edr ON dcte.ID = edr.department_id;
```

e、查询没有参与任何项目的员工姓名

```
SELECT e.name
FROM employee e
LEFT JOIN emp_pro_relation epr ON e.ID = epr.employee_id
WHERE epr.employee_id IS NULL;
```

