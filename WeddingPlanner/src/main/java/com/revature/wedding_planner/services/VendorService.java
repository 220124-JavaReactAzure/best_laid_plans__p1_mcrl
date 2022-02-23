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

//		// logic that verifies the new users information isn't duplicated in the system
//		boolean usernameAvailable = userDAO.findByUsername(newUser.getUsername()) == null;
//		boolean emailAvailable = userDAO.findByEmail(newUser.getEmail()) == null;
//
//		if (!usernameAvailable || !emailAvailable) {
//			if (emailAvailable) {
//				throw new ResourcePersistenceException("The provided username was already taken in the database");
//			} else if (usernameAvailable) {
//				throw new ResourcePersistenceException("The provided email was already taken in the database");
//			} else {
//				throw new ResourcePersistenceException(
//						"The provided username and email were already taken in the database");
//			}
//		}

		Vendor persistedUser = vendorDAO.create(newVendor);

		if (persistedUser == null) {
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

	public Vendor getVendorByID(String vendorId) {

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
	
	public void deleteUser(Vendor vendor) {
//		vendorDAO.delete(vendor.getId());
	}
}
