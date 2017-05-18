insert ignore into sec_group(groupname, groupdesc) values('CUSTOMERS','This group contains customers.');
insert ignore into sec_group(groupname, groupdesc) values('EMPLOYEES','This group contains employees.');

insert ignore into sec_user(username, password) values('manoj',SHA2('msri',256)); 
insert ignore into sec_user(username, password) values('customer2',SHA2('customer2',256)); 
insert ignore into sec_user(username, password) values('siri',SHA2('joy',256));
insert ignore into sec_user(username, password) values('shru',SHA2('shru',256)); 
 

insert ignore into sec_user_groups(username, groupname) values('manoj','CUSTOMERS');
insert ignore into sec_user_groups(username, groupname) values('customer2','CUSTOMERS');
insert ignore into sec_user_groups(username, groupname) values('siri','EMPLOYEES');
insert ignore into sec_user_groups(username, groupname) values('shru','EMPLOYEES');



INSERT INTO product(ID,AUTHOR,CATEGORYNAME,GENRE,PRICE,PRODUCTNAME,PRODUCTQUANTITY,SUMMARY,YOP,IMAGE)VALUE(11,'Ravi','Book','Novel',15,'Himalayan Blunder',10,'Trails of himalayas','2006-02-03','https://politicallyunpolitical.files.wordpress.com/2015/10/3.jpg');
INSERT INTO product(ID,AUTHOR,CATEGORYNAME,GENRE,PRICE,PRODUCTNAME,PRODUCTQUANTITY,SUMMARY,YOP,IMAGE)VALUE(12,'George','Magazine','Weekly',20,'Fasion Week',50,'Fashion world','2016-06-08','http://www.spd.org/zooey-deschanel-lucky-magazine-march-2014-cover_1_thumb_w_580.jpg');
INSERT INTO product(ID,AUTHOR,CATEGORYNAME,GENRE,PRICE,PRODUCTNAME,PRODUCTQUANTITY,SUMMARY,YOP,IMAGE)VALUE(13,'Brian','News Paper','Daily',5,'Chicago Tribune',50,'A daily News Paper','2017-04-04','http://pullzone2.jktpartnersllc.netdna-cdn.com/wp-content/uploads/2011/06/Chicago-Tribune.png?x18283');
INSERT INTO product(ID,AUTHOR,CATEGORYNAME,GENRE,PRICE,PRODUCTNAME,PRODUCTQUANTITY,SUMMARY,YOP,IMAGE)VALUE(14,'Hiroki Sayama','Text Book','Engineering',200,'Modeling and Analysis',10,'Intro to Modeling and Analysis','2010-06-08','https://www.bu.edu/networks/files/2016/01/sayamabook-781x1024.png');
INSERT INTO orderdetails(ID,ORDEREDON,PRODUCTPRICE,QUANTITY,TOTAL,PRODUCT_ID)VALUES(1,'2017-03-05',15,2,30,11);
INSERT INTO orderdetails(ID,ORDEREDON,PRODUCTPRICE,QUANTITY,TOTAL,PRODUCT_ID)VALUES(2,'2017-03-05',15,2,30,12);
INSERT INTO customer(ID,ADDRESS,CONTACTNO,EMAIL,FIRSTNAME,LASTNAME,USERNAME,REGISTEREDDATE)VALUES(21,'2951 S King Dr, 1560',9632587412,'manoj@gmail.com','Manoj','Sri','manoj',current_timestamp);
INSERT INTO customer(ID,ADDRESS,CONTACTNO,EMAIL,FIRSTNAME,LASTNAME,USERNAME,REGISTEREDDATE)VALUES(22,'2951 S King Dr,Apt 1910',9568741236,'customer2@gmail.com','Soumya','Puranik','customer2',current_timestamp);
INSERT INTO ordertable(ID,ADDRESS,MOBILENUMBER,ORDERDATE,STATUS,TOTALPRICE,CUSTOMER_ID,ORDERREVIEW_ID)VALUES(1,'2951 S King Dr, 1560',9632587412,'2017-03-05','Pending',30,21,null);
INSERT INTO ordertable(ID,ADDRESS,MOBILENUMBER,ORDERDATE,STATUS,TOTALPRICE,CUSTOMER_ID,ORDERREVIEW_ID)VALUES(2,'2951 S King Dr, 1489',9632587412,'2017-03-05','Pending',30,21,null);
INSERT INTO ordertable_orderdetails(OrderTable_ID,orderdetails_ID)VALUES(1,1);
INSERT INTO ordertable_orderdetails(OrderTable_ID,orderdetails_ID)VALUES(2,2);
INSERT INTO employee(ID,ADDRESS,CONTACTNO,EMAIL,FIRSTNAME,LASTNAME,USERNAME,REGISTEREDDATE)VALUES(61,'2678 S King Dr',9547863214,'siri@gmail.com','Siri','Joy','siri',current_timestamp);
INSERT INTO employee(ID,ADDRESS,CONTACTNO,EMAIL,FIRSTNAME,LASTNAME,USERNAME,REGISTEREDDATE)VALUES(62,'2801 S King Dr',9896561214,'shru@gmail.com','Shruti','Puranik','shru',current_timestamp);
INSERT INTO orderreview(ID,DATE,DELIVERYDATE,SHIPPINGDETAILS,EMPLOYEE_ID,ORDERTABLE_ID)VALUES(31,'2017-03-06','2017-03-10','USPS Chicago',61,1);
UPDATE ordertable SET ORDERREVIEW_ID='31' WHERE ID='1';
