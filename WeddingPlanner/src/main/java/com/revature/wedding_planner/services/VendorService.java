package com.revature.wedding_planner.services;


import com.revature.wedding_planner.models.Vendor;
import com.revature.wedding_planner.daos.VendorDAO;

import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;

import java.util.List;


public class VendorService {
	private final VendorDAO vendorDAO;

	public VendorService(VendorDAO vendorDAO) {
		this.vendorDAO = vendorDAO;
	}

	public boolean addVendor(Vendor newVendor) {
		if (!isValidVendor(newVendor)) {
			throw new InvalidRequestException("Invalid vendor data provided");
		}


		Vendor persistedVendor = vendorDAO.create(newVendor);

		if (persistedVendor == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return true;
	}

	public Vendor authenticateVendor(String vendorName) {

		Vendor authenticatedVendor = vendorDAO.findByVendorName(vendorName);

		if (authenticatedVendor == null) {
			throw new AuthenticationException(
					"Unauthenticated user, information provided was not found in our database.");
		}

			return authenticatedVendor;
	}
	

	public boolean isValidVendor(Vendor vendor) {
		// TODO check if each field is valid depending on the user type
		if (vendor != null) {
			return true;
		}
		return false;
	}

	public void logout() {
		// TODO or not to do?
	}

	public Vendor updateVendor(Vendor updatedVendor) {
		if (!vendorDAO.update(updatedVendor)) {
			throw new ResourcePersistenceException("Failure updating user.");
		}
		return updatedVendor;
	}

	public Vendor getVendorByID(int vendorId) {

		Vendor foundVendor = vendorDAO.findById(vendorId);

		if (foundVendor != null) {
			return foundVendor;
		} else {
			return null;
		}
	}

	public List<Vendor> getAllVendors() {
		List<Vendor> allVendors = vendorDAO.findAll();
		
		if (allVendors == null) {
			return null;
		} else {
			return allVendors;
		}
	}
	
	public void deleteVendor(Vendor vendor) {
		vendorDAO.delete(vendor.getId());
	}
}
