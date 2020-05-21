# IoTBay
## ISD Project (Assignment 2)
A prototype Java web application for an online e-commerce website.

## Database Setup (including sample data)
1. Open `IoTBay` project folder in [NetBeans IDE 8.2](https://netbeans.org/downloads/8.2/rc/)
2. In '`Web Pages` > `db`', run (in order):
    1. `createTables.sql`
    2. `loadSampleCustomers.sql`
    3. `loadSampleStaff.sql`
    4. `loadSampleDevice.sql`
    5. `loadSampleShippingCompany.sql`
    6. `loadSampleCustomerOrder.sql`
    7. `loadSampleOrderDeviceT.sql`
    8. `loadSamplePaymentDetails.sql`
    9. `loadSampleCartItem.sql`
    10. `loadSampleApplicationAccessLogs-staff.sql`
    11. `loadSampleApplicationAccessLogs-customer.sql`
    12. `loadSampleSupportTicket.sql`

## Usage

### Method 1: Run from project

1. Open `IoTBay` project folder in [NetBeans IDE 8.2](https://netbeans.org/downloads/8.2/rc/)
2. Run project

### Method 2: Run from WAR file

1. Unzip `isd-at1-iotbay.war`
2. Create a subfolder named `web` inside the unzipped folder
3. Move all files in the unzipped folder into `web`
4. Open [NetBeans IDE 8.2](https://netbeans.org/downloads/8.2/rc/) > New Project > Java Web > Web Application with Existing Sources
5. Set the Location field to the unzipped folder
6. Use default values for all other fields
7. Run project