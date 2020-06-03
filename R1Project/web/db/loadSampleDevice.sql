insert into device (devicename, type, cost, stockquantity, description)values 

('DHT11', 'Climate Sensor', 10.99, 93, 'The DHT11 and DHT22 sensors can measure humidity as well as temperature. Only one GPIO is used. The difference between the two is mainly the measuring range and accuracy. The white DHT22 can measure all humidity ranges from 0-100% with an accuracy of 2%. By comparison, the DHT11 (blue) is only able to measure areas of 20-90% humidity and above all, the accuracy is significantly worse with 5%. The light blue DHT11 sensor has a small price advantage.'),

('DS18B20', 'Climate Sensor', 5.99, 102, 'The DS18B20 represents a very simple sensor. These Raspberry Pi sensors are addressed via the so-called 1-wire bus. An advantage is that many different 1-wire components can be connected in series and read out by a single GPIO. However, these modules can not measure additional information such as humidity and / or air pressure. The DS18B20 is particularly suitable for outdoor use, as there are also water resistant versions available.'),

('BMP180 Barometer', 'Climate Sensor', 8.99, 238, 'The determination of the air pressure can be meaningful in weather stations and similar projects. This is best done using the BMP180, which is controlled via I2C on the Raspberry Pi. In addition to the air pressure, the temperature can be read out as well as the altitude. However, the last value is not very accurate. If you need the height, you should read the values with a GPS receiver.'), 

('MQ-2 Gas Sensor', 'Climate Sensor', 2.99, 290, 'The MQ gas sensors can detect different gases at room temperature. Depending on the model, other gases are supported. The MQ-2 can recognise methane, butane, LPG and smoke, the MQ3 detects, for example, alcohol, ethanol and smoke, etc. You should take care that these sensors can be very hot and they should not be touched directly.'),

('PIR Motion Sensor', 'Motion Sensor', 1.99, 720, 'The PIR motion sensor has some advantages over other similar products: besides the low price, a signal is sent only if something moves. This allows you to wait for signal flanks using the GPIOs. In addition, a resistance can be varied so that a signal is only sent when the movement is close, or changes that are already far away are perceived.'),

('HC-SR04 ultrasonic', 'Motion Sensor',  3.89, 43, 'The HC-SR04 sensor is not a distance / motion detector, but an ultrasonic sensor. Through a small trick it is nevertheless possible to measure distances. By measuring the time elapsed between transmitting and receiving an ultrasound signal, you can derive the distance as the sound velocity in the air is known.'),

( 'Magnetic Switch ', 'Motion Sensor', 3.90, 78,  'By means of magnetic sensors / reed relay, you can check for binary states. The magnetic relay is opened as soon as a magnet is in the vicinity. Otherwise the access is closed. So if voltage is then passed through, you can check the condition.'), 

( 'GP2Y0A02YK', 'Motion Sensor', 12.99, 821,  'With the GP2Y0A02YK infrared distance meter, much more accurate measurements can be performed, as with e.g. the HC-SR04. The module is limited to a range of 20-150cm. Alternatively, the similar sensor GP2Y0A710K0F can be used, which has a range of 100 to 500cm.'), 

('RFID card reader', 'Motion Sensor', 4.99, 67, 'The RFID-RC522 is a card reader for check cards. A signal is transmitted via the SPI data bus as soon as a card approaches on a few centimeters. Each card has a different code, which you can read out. Thus, for example, locks and / or doors could be realized, which open without contact – sesame open up.'),

('GPS NEO-6M Mod', 'Navigation Sensor', 10.99, 789, 'The most common and best known GPS receiver is the NEO-6M module. All GPS position data can be determined with the help of the orbiting satellites.'), 

('USB GPS Receiver', 'Navigation Sensor', 14.99, 835,   'As an alternative to GPS modules which are connected via the GPIOs, USB GPS receivers can also be used. Those have the advantage that (almost) all are compatible with Windows, Linux and Mac and no additional connection is necessary.'),

('MPU-6050 Gyroscope', 'Navigation Sensor', 2.99, 738, 'A gyroscope (circular instrument) is used to detect the rotation along the three axes. It also contains an acceleration sensor which can be used in features such as robot arms to determine the angle of rotation.'),

('GY-271 Compass', 'Navigation Sensor', 5.99, 156, 'As with analogue compasses, the directional display can also be read digitally. The HMC5883L sensor, which is read out via I2C, which returns an angle in radians, is suitable for this purpose. As with a normal compass, the value can be confounded by metal objects nearby.'),

('DS1307 RTC', 'Navigation Sensor', 3.99, 792,  'A Realtime Clock or RTC module once initialized, saves the current time even if the power supply is not present due to the small battery. This applies in applications where no (permanent) Internet connection is given, but the date and the exact time is important (car PC, weather station, etc.'),

('433 MHz Set', 'Wireless Sensor', 2.99, 553, 'A simple method to transmit signals via radio are 433 MHz transmitter and receiver. Since these sets are very cheap, they are used in many projects.'),

('2.4 NRF24L01', 'Wireless Sensor',  4.99, 245, 'A more advanced method for wireless communication is the use of the 2.4 GHz frequency. The advantages compared to the 433 MHz transmission rate are mainly that a larger amount of data can be transferred at once, thus, whole sentences and commands can be sent with a signal or data package.' ),

('GSM Surfstick', 'Wireless Sensor', 35.90, 153, 'The Raspberry Pi is used in many outdoors projects, such as a weather station or for monitoring certain things. However, even if no WIFI signal is available, many functions are restricted.'),

('Infrared diodes', 'Wireless Sensor', 3.99, 783, 'Most remote controls use infrared LEDs to transmit signals, these codes can be read and stored easily with an infrared receiver. With the program LIRC - it is also possible to send those codes with an IR transmitter diode'),

('Laser Module', 'Wireless Sensor', 2.50, 173, 'Although standard laser modules do not have great functionality, they are used in various interesting projects. Thus, for example, there are projects of distance measuring devices, which are using a camera and a laser module. The laser is switched on and off very quickly and pictures are recorded.' ),

('Moisture Sensor', 'Climate Sensor', 9.99, 723, 'This analogue humidity sensor finds an excellent place in automatic irrigation systems. It is placed in the ground and measures the humidity by current flowing between the strands. The more humid the earth in between, the higher the (analog) signal.'),

('Si4703 Radio', 'Wireless Sensor', 10.00, 372, 'The Si470x module offers the option to upgrade the Pi to a radio receiver, which can be very interesting in Car PCs or Raspberry Pi Jukeboxes.'),

('Power sockets', 'Wireless Sensor', 15.99, 215, 'In the field of home automation, wireless sockets are almost a standard, the vast majority of these devices work with 433 MHz radio signals. By reading the codes of the remote control with a receiver on the Raspberry Pi, one can switch these radio sockets individually.')





/*
insert into device (devicename, cost, stockquantity) values ('Metaproterenol Sulfate', 796.71, 93),
('TIMENTIN', 733.64, 22),
('SKIN CAVIAR CONCEALER FOUNDATION SUNSCREEN SPF 15 - Golden Beige', 411.67, 43),
('Doxycycline Hyclate', 995.07, 53),
('Glucotrol', 368.02, 67),
('Sodium Chloride', 771.86, 91),
('Quality Choice Castor Oil', 940.04, 70),
('benefit DREAM SCREEN BROAD SPECTRUM SPF 45 SUNSCREEN FOR FACE', 268.21, 69),
('Adoxa', 138.58, 1),
('SHISEIDO WHITE LUCENT BRIGHTENING SPOT-CONTROL FOUNDATION (REFILL)', 265.61, 71),
('medroxyprogesterone acetate', 358.25, 53),
('Sweet Papaya Antibacterial Foaming Hand Wash', 158.96, 70),
('Revatio', 283.46, 84),
('Gemfibrozil', 275.91, 54),
('Provocholine', 510.4, 51),
('Anti-Dandruff', 771.09, 24),
('Cool Ice', 474.44, 32),
('Ban', 242.84, 63),
('IMITREX', 945.28, 63),
('Select Brand Sunscreen', 747.52, 26),
('SHISEIDO THE MAKEUP PERFECT SMOOTHING COMPACT FOUNDATION (Refill)', 925.15, 47),
('AcetaZOLAMIDE', 900.47, 85),
('Loratadineantihistamine', 773.35, 41),
('POTASSIUM CHLORIDE', 443.58, 44),
('ESIKA', 673.36, 84),
('Cystadane', 298.53, 19),
('Firefly Angry Birds Fluoride', 178.69, 96),
('Nite Time', 712.61, 61),
('Estradiol', 195.5, 22),
('COUGH HICCOUGH', 74.51, 59),
('Oxygen', 473.2, 34),
('Gelsemium Semp Kit Refill', 538.23, 71),
('Trihexyphenidyl Hydrochloride', 79.79, 67),
('Atenolol', 397.04, 90),
('Butrans', 7.49, 85),
('Capsicum Chamomilla', 306.99, 2),
('Hazelnut', 376.19, 33),
('Non-Drowsy Formula Wal-Tussin', 60.31, 92),
('ZOVIRAX', 589.63, 88),
('Metronidazole', 632.13, 26),
('Heartburn', 108.36, 23),
('Lunesta', 574.56, 78),
('Flurbiprofen', 589.82, 28),
('Penicillium notatum', 318.83, 72),
('Animi-3', 513.81, 7),
('Lamotrigine', 614.07, 95),
('Pleo Citro', 449.61, 70),
('First aid and Burn', 690.43, 33),
('Thioridazine Hydrochloride', 442.89, 96),
('methylphenidate hydrochloride', 839.39, 60),
('Anxiety', 563.22, 22),
('health mart allergy complete d', 28.21, 65),
('Oxybutynin Chloride', 624.41, 61),
('Ipratropium Bromide and Albuterol Sulfate', 851.17, 35),
('mycophenolate mofetil', 778.07, 49),
('Infed', 718.22, 58),
('Bisoprolol Fumarate and Hydrochlorothiazide', 317.27, 81),
('Glyburide and Metformin', 660.75, 20),
('Glipizide', 448.64, 86),
('Enviro', 310.21, 67),
('DOLOPHINE HYDROCHLORIDE', 569.24, 36),
('the Balm BalmShelter tinted moisturizer SPF 18 broad spectrum medium dark', 371.73, 80),
('Oxygen', 551.35, 15),
('O BlancTis', 375.32, 14),
('Micardis HCT', 966.06, 16),
('Sunmark pain reliever', 735.19, 59),
('Sorbitol', 860.45, 43),
('Professional Therapy Muscle Care Pain Relieving Gel by Dr. Chris Oswald', 581.27, 76),
('Colchicum Chelidonium', 178.52, 71),
('Leader Fiber', 451.32, 39),
('Gallplex', 424.62, 87),
('Green (Sting) Bean', 326.01, 52),
('DOXEPIN HYDROCHLORIDE', 29.66, 69),
('CARE ONE', 338.25, 20),
('Lansoprazole', 972.65, 73),
('family wellness hemorrhoidal', 520.23, 26),
('Trexall', 99.28, 44),
('esika', 179.65, 86),
('Bupropion Hydrochloride', 703.75, 98),
('Soltamox', 835.28, 9),
('Giorgio Armani Crema Nera OMC3', 593.6, 87),
('Grief and Guilt Reliever', 117.85, 16),
('Bupropion Hydrochloride', 909.38, 17),
('Lorazepam', 276.29, 24),
('Fentanyl', 483.94, 83),
('Alprazolam', 979.88, 92),
('Amoxicillin', 60.74, 15),
('A Mold Mixture', 930.1, 37),
('Cyproheptadine Hydrochloride', 79.46, 13),
('Mirtazapine', 859.74, 74),
('Denti-Care Denti-Freeze', 625.0, 62),
('ISOVUE-M 200', 49.7, 5),
('Antacid Antigas Maximum Strength', 873.52, 84),
('Aveeno Active Naturals Positively Ageless Correcting Tinted Moisturizer', 556.59, 22),
('Anacard Orient Kit Refill', 373.72, 7),
('equaline complete', 413.03, 76),
('TopCare Sore Throat Cherry Flavor', 552.04, 87),
('Warfarin Sodium', 828.01, 48),
('Superdent', 361.11, 10),
('Naltrexone Hydrochloride', 353.99, 67);
*/