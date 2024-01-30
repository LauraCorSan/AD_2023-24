/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistenciadbemp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author laura
 */
@Entity
@Table(name = "emp", catalog = "bdemp", schema = "")
@NamedQueries({
    @NamedQuery(name = "Emp.findAll", query = "SELECT e FROM Emp e"),
    @NamedQuery(name = "Emp.findByEmpno", query = "SELECT e FROM Emp e WHERE e.empno = :empno"),
    @NamedQuery(name = "Emp.findByEname", query = "SELECT e FROM Emp e WHERE e.ename = :ename"),
    @NamedQuery(name = "Emp.findByJob", query = "SELECT e FROM Emp e WHERE e.job = :job"),
    @NamedQuery(name = "Emp.findByMgr", query = "SELECT e FROM Emp e WHERE e.mgr = :mgr"),
    @NamedQuery(name = "Emp.findByHiredate", query = "SELECT e FROM Emp e WHERE e.hiredate = :hiredate"),
    @NamedQuery(name = "Emp.findBySal", query = "SELECT e FROM Emp e WHERE e.sal = :sal"),
    @NamedQuery(name = "Emp.findByComm", query = "SELECT e FROM Emp e WHERE e.comm = :comm"),
    @NamedQuery(name = "Emp.findByDeptno", query = "SELECT e FROM Emp e WHERE e.deptno = :deptno")})
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMPNO", nullable = false)
    private Integer empno;
    @Column(name = "ENAME", length = 2)
    private String ename;
    @Column(name = "JOB", length = 2)
    private String job;
    @Column(name = "MGR")
    private Integer mgr;
    @Column(name = "HIREDATE")
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SAL", precision = 7, scale = 2)
    private BigDecimal sal;
    @Column(name = "COMM", precision = 7, scale = 2)
    private BigDecimal comm;
    @Basic(optional = false)
    @Column(name = "DEPTNO", nullable = false)
    private int deptno;

    public Emp() {
    }

    public Emp(Integer empno) {
        this.empno = empno;
    }

    public Emp(Integer empno, int deptno) {
        this.empno = empno;
        this.deptno = deptno;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empno != null ? empno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emp)) {
            return false;
        }
        Emp other = (Emp) object;
        if ((this.empno == null && other.empno != null) || (this.empno != null && !this.empno.equals(other.empno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.persistenciadbemp.Emp[ empno=" + empno + " ]";
    }
    
}
