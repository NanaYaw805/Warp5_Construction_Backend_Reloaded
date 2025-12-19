Equipment Rental System Documentation
Overview
A Spring Boot-based REST API for managing construction equipment rentals. The system allows users to list equipment, submit rental requests, and manage rental approvals.
Table of Contents
1. Entities
2. DTOs
3. Controllers
4. Services
5. API Endpoints
6. Data Models
Entities
1. Equipment (Equipment.java)
Represents a construction equipment item available for rent.
Fields:
* id (Long): Primary key, auto-generated sequence
* name (String): Equipment name (required)
* ownerId (Long): ID of the equipment owner (required)
* ownerName (String): Name of the equipment owner (required)
* location (String): Equipment location
* imageOne, imageTwo, imageThree (String): Image URLs for equipment
* description (String): Detailed description (TEXT column)
* price (double): Rental price per period
* rating (double): User rating (0-5 scale)
* availability (boolean): Whether equipment is currently available
* createdAt (LocalDateTime): Timestamp of creation
2. RentalRequest (RentalRequest.java)
Represents a rental request from a user to rent equipment.
Fields:
* id (Long): Primary key, auto-generated
* equipmentId (Long): ID of requested equipment
* equipmentName (String): Name of requested equipment
* renterId (Long): ID of user requesting rental
* renterName (String): Name of user requesting rental
* ownerId (Long): ID of equipment owner
* rentalAmount (String): Rental cost/amount
* rentalEmail (String): Renter's contact email
* rentalPhoneNumber (String): Renter's contact phone
* startDate (LocalDate): Rental start date
* endDate (LocalDate): Rental end date
* status (String): Request status - defaults to "PENDING"
* createdAt (LocalDateTime): Request creation timestamp
* updatedAt (LocalDateTime): Last update timestamp
Status Values:
* PENDING: Initial state, awaiting owner decision
* APPROVED: Owner accepted the request
* REJECTED: Owner declined the request
DTOs (Data Transfer Objects)
Request DTOs
1. EquipmentRequest (EquipmentRequest.java)
Used for creating/updating equipment.
Fields:
* name (String): Equipment name
* ownerId (Long): Owner ID
* ownerName (String): Owner name
* location (String): Location
* description (String): Description
* price (Double): Rental price
* rating (Double): Rating
* availability (Boolean): Availability status
* imageOne, imageTwo, imageThree (String): Image URLs
2. RentalRequestCreateDto (RentalRequestCreateDto.java)
Used for creating rental requests.
Fields:
* equipmentId (Long): Equipment ID
* equipmentName (String): Equipment name
* renterId (Long): Renter ID
* renterName (String): Renter name
* ownerId (Long): Owner ID
* rentalAmount (String): Rental amount
* rentalEmail (String): Contact email
* rentalPhoneNumber (String): Contact phone
* startDate (LocalDate): Start date
* endDate (LocalDate): End date
3. RentalDecisionDto (RentalDecisionDto.java)
Used for updating rental request status.
Fields:
* decision (String): "APPROVED" or "REJECTED"
Response DTOs
1. EquipmentResponse (EquipmentResponse.java)
Response object for equipment data.
Fields:
* All fields from Equipment entity plus:
* id (Long): Equipment ID
* createdAt (LocalDateTime): Creation timestamp
2. RentalRequestResponse (RentalRequestResponse.java)
Response object for rental request data.
Fields:
* All fields from RentalRequest entity
Controllers
1. EquipmentController (EquipmentController.java)
Handles equipment-related operations.
Base Path: /api/equipment
Endpoints:
* POST / - Create new equipment
* GET / - Get all equipment
* GET /{id} - Get equipment by ID
2. RentalRequestController (RentalRequestController.java)
Handles rental request operations.
Base Path: /api/rentals
Endpoints:
* POST / - Create new rental request
* GET /owner/{ownerId} - Get all requests for an owner
* GET /renter/{renterId} - Get all requests for a renter
* PATCH /{id}/decision - Update request status (approve/reject)
Services
1. EquipmentService (EquipmentService.java Interface)
Defines equipment management operations.
Methods:
* createEquipment(EquipmentRequest request) - Create equipment
* getAllEquipment() - Retrieve all equipment
* getEquipmentById(Long id) - Get equipment by ID
2. EquipmentServiceImpl (EquipmentServiceImpl.java)
Implements equipment service logic.
Features:
* Maps DTOs to entities
* Handles database operations via EquipmentRepository
* Converts entities to response DTOs
3. RentalRequestService (RentalRequestService.java Interface)
Defines rental request operations.
Methods:
* create(RentalRequestCreateDto dto) - Create rental request
* getRequestsForOwner(Long ownerId) - Get owner's requests
* getRequestsForRenter(Long renterId) - Get renter's requests
* decide(Long requestId, String decision) - Approve/reject request
4. RentalRequestServiceImpl (RentalRequestServiceImpl.java)
Implements rental request service logic.
Features:
* Creates rental requests with PENDING status
* Validates decision input (only "APPROVED" or "REJECTED")
* Updates timestamps on status changes
* Maps entities to response DTOs
API Endpoints
Equipment Endpoints
Create Equipment
http
POST /api/equipment
Content-Type: application/json

{
    "name": "Excavator",
    "ownerId": 1,
    "ownerName": "John Doe",
    "location": "New York",
    "description": "Large excavator for construction",
    "price": 500.00,
    "rating": 4.5,
    "availability": true,
    "imageOne": "url1.jpg",
    "imageTwo": "url2.jpg",
    "imageThree": "url3.jpg"
}
Get All Equipment
http
GET /api/equipment
Get Equipment by ID
http
GET /api/equipment/{id}
Rental Request Endpoints
Create Rental Request
http
POST /api/rentals
Content-Type: application/json

{
    "equipmentId": 1,
    "equipmentName": "Excavator",
    "renterId": 2,
    "renterName": "Jane Smith",
    "ownerId": 1,
    "rentalAmount": "500.00",
    "rentalEmail": "jane@email.com",
    "rentalPhoneNumber": "123-456-7890",
    "startDate": "2024-12-01",
    "endDate": "2024-12-07"
}

