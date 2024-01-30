/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistenciadbemp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author laura
 */
@Entity
@Table(name = "dept", catalog = "bdemp", schema = "")
@NamedQueries({
    @NamedQuery(name = "Dept.findAll", query = "SELECT d FROM Dept d"),
    @NamedQuery(name = "Dept.findByDeptno", query = "SELECT d FROM Dept d WHERE d.deptno = :deptno"),
    @NamedQuery(name = "Dept.findByDname", query = "SELECT d FROM Dept d WHERE d.dname = :dname"),
    @NamedQuery(name = "Dept.findByLoc", query = "SELECT d FROM Dept d WHERE d.loc = :loc")})
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEPTNO", nullable = false)
    private Integer deptno;
    @Column(name = "DNAME", length = 3)
    private String dname;
    @Column(name = "LOC", length = 3)
    private String loc;

    public Dept() {
    }

    public Dept(Integer deptno) {
        this.deptno = deptno;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptno != null ? deptno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dept)) {
            return false;
        }
        Dept other = (Dept) object;
        if ((this.deptno == null && other.deptno != null) || (this.deptno != null && !this.deptno.equals(other.deptno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.persistenciadbemp.Dept[ deptno=" + deptno + " ]";
    }
    
}
