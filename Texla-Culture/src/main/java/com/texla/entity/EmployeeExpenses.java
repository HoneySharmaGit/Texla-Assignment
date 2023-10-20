package com.texla.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeExpenses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_no")
	private int sNo;

	private String uniqueId;

	private double travelExpense;

	private double foodExpense;

	private double accommodationExpense;

	private double otherExpense;

	private double totalExpense;

	private String createdAt;

	private String deletedAt;

	private boolean isDeleted = false;

	private String employeeName;

	private String employeeId;

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public double getTravelExpense() {
		return travelExpense;
	}

	public void setTravelExpense(double travelExpense) {
		this.travelExpense = travelExpense;
	}

	public double getFoodExpense() {
		return foodExpense;
	}

	public void setFoodExpense(double foodExpense) {
		this.foodExpense = foodExpense;
	}

	public double getAccommodationExpense() {
		return accommodationExpense;
	}

	public void setAccommodationExpense(double accommodationExpense) {
		this.accommodationExpense = accommodationExpense;
	}

	public double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(double otherExpense) {
		this.otherExpense = otherExpense;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
