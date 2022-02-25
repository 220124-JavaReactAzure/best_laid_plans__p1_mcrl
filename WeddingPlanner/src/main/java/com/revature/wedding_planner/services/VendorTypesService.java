package com.revature.wedding_planner.services;



import com.revature.wedding_planner.models.Vendor;
import com.revature.wedding_planner.models.VendorType;

import java.util.List;

import com.revature.wedding_planner.daos.VendorTypesDAO;

import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.daos.VendorTypesDAO;
public class VendorTypesService {

		private final VendorTypesDAO vendorTypesDAO;

		public VendorTypesService(VendorTypesDAO vendorTypesDAO) {
			this.vendorTypesDAO = vendorTypesDAO;
		}
		
		public boolean addVendorType(VendorType newVendorType) {
			if (!isValidVendorType(newVendorType)) {
				throw new InvalidRequestException("Invalid vendorType data provided");
			}
			
			VendorType persistedVendorType = vendorTypesDAO.create(newVendorType);

			if (persistedVendorType == null) {
				throw new ResourcePersistenceException("The user could not be persisted");
			}

			return true;	
	}

		public VendorType authenticateVendor(String vendorTypeName) {

			VendorType authenticatedVendorType = vendorTypesDAO.findByVendorName(vendorTypeName);

			if (authenticatedVendorType == null) {
				throw new AuthenticationException(
						"Unauthenticated vendor type, information provided was not found in our database.");
			}

				return authenticatedVendorType;
		}	
		
		public boolean isValidVendorType(VendorType vendorType) {
			// TODO check if each field is valid depending on the user type
			if (vendorType != null) {
				return true;
			}
			return false;
		}

		public void logout() {
			// TODO or not to do?
		}

		public VendorType updateVendor(VendorType updatedVendorType) {
			if (!vendorTypesDAO.update(updatedVendorType)) {
				throw new ResourcePersistenceException("Failure updating user.");
			}
			return updatedVendorType;
		}

		public VendorType getVendorByID(int vendorTypeId) {

			VendorType foundVendorType = vendorTypesDAO.findById(vendorTypeId);

			if (foundVendorType != null) {
				return foundVendorType;
			} else {
				return null;
			}
		}
		public static List<VendorType> getAllVendorTypes() {
			List<VendorType> allVendorTypes = vendorTypesDAO.findAll();
			
			if (allVendorTypes == null) {
				return null;
			} else {
				return allVendorTypes;
			}
		}
		public void deleteVendorType(VendorType vendorType) {
			vendorTypesDAO.delete(vendorType.getVendTypeId());
		}
		
}
