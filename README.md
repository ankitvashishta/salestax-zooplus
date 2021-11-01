# salestax

<h3>Business Context</h3>

Basic sales tax is applicable at a rate of 10% on all goods, except books, food and medical products which are exempted. Import duty is an additional
sales tax applicable on all imported goods at a rate of 5%, with no exemptions.<br><br>
When I purchase items, I receive a receipt that lists the name of all the items, respective quantity and their price (including tax), finishing with the total cost
of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (n*p/100
rounded up to the nearest 0.05) amount of sales tax.<br><br>

<h3>Task Description</h3>
Write an application that, for a given input containing a list of items, a receipt is printed out with the purchase details including the total cost of items and
total sales taxes amount.<br>

<h3>Solution</h3>
<h6>Configurations</h6>
Sales Tax Rate, Imported Tax Rate, Exempted Items as a comma separated list, Fraction to round up values and decimal format for output are configurable and can be updated in <b>src/main/resources/application.properties</b> file.

<h6>Testing</h6>
User input is provided in text files in <b>src/test/resources</b> folder.

<h6>Execution</h6>
This is a maven project compiled using jdk 11.<br>
To run the application, execute the following command from the folder containing the jar file :<br>
<b>java -jar salestax-zooplus-0.0.1-SNAPSHOT.jar</b><br>
OR<br>
If using an IDE, run the main class - <b>SalesTaxApplication.java</b>

<h6>Assumptions</h6>
The imported items contain the keyword imported.

<h4>Input and Output samples</h4>
<h5>Input 1:</h5>
1 book at 12.49<br>
1 music CD at 14.99<br>
1 chocolate bar at 0.85<br>
<h5>Output 1:</h5>
1 book : 12.49<br>
1 music CD: 16.49<br>
1 chocolate bar: 0.85<br>
Sales Taxes: 1.50<br>
Total: 29.83<br>

<h5>Input 2:</h5>
1 imported box of chocolates at 10.00<br>
1 imported bottle of perfume at 47.50<br>
<h5>Output 2:</h5>
1 imported box of chocolates: 10.50<br>
1 imported bottle of perfume: 54.65<br>
Sales Taxes: 7.65<br>
Total: 65.15<br>





