This is the Latest DF2H App Code as of 08/16/2019 till 08/18/2019.
Contains  Below Chnages: 
A.CORS Issue fixes across all the controllers - 08/16/2019.
B. Except Update and Delete for all Entities - Testing Still Pending

08-18-2019:
A. Adding Tables  - OrderDetails,PaymentDetails
B. Changes to Item Table
B. New Table Order Item added to store the Items in the Order.
C.ER Changes to below :
	Consumer - OrderDetails [One- many] 
	OrderDetails - OrderItem  [One- many]
	OrderDetails - PaymentDetails[One - One]
	
08-19-2019:
Integrating PayUMoney RequestHashCode Generation.
Order Id Generation as well.

09/13/2019:
Done changes for Password Reset in UserDetailsController

09/15/2019:
Done changes for Order Details.
Added Loader

10/10/2019:
Done Changes for Farmer,Supplier,Consumer registration Status from userRegistrationStatus to registrationStatus. 	
Added method to OrderDetails to get the List of Orders placed by Consumer ID.
	
Used S3 bucket of herambh AWS Account.

12/10/2019:
Changed CategoryController to fetch by Path Variables ID and NAME.

19/10/2019:
Changes to Item Model Include :
1.Added unitOfSale column.
2.Changed the unitCost to Float.

29/10/2019:
Adding Swagger for API Documentation.

30/10/2019:
Done changes for Email,changes in application.prop
OrderItem's column : <total_cost changed to float(6,2)>

