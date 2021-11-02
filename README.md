# salestax

<ul>
<li>
<h3>Business Context</h3>

Basic sales tax is applicable at a rate of 10% on all goods, except books, food and medical products which are exempted. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.<br>
When I purchase items, I receive a receipt that lists the name of all the items, respective quantity and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (n*p/100 rounded up to the nearest 0.05) amount of sales tax.</li>

<li><h3>Task Description</h3>
Write an application that, for a given input containing a list of items, a receipt is printed out with the purchase details including the total cost of items and total sales taxes amount.</li>

<li><h3>Solution</h3>
<ul>
<li>
<h5>Configurations</h5>
Following properties are kept configurable, and can be updated in <b>src/main/resources/application.properties</b> file.
<ol>
<li>Sales Tax Rate</li>
<li>Imported Tax Rate</li>
<li>Exempted Items as a comma separated list</li>
<li>Fraction to round up values</li>
<li>Decimal format for output</li>
</ol>
</li>
<li><h5>Assumptions</h5>
<ol>
<li>The imported items contain the keyword imported.</li>
<li>Exempted items are the ones configured in properties file</li>
</ol>
</li>
<li><h5>Test Cases</h5>
Junit test cases are written and sample user input is provided in text files in <b>src/test/resources</b> folder.</li>

<li><h5>Compilation</h5>
This is a maven project compiled using jdk 11.<br>
To compile the project and create an executable jar, run : <b>mvn clean install</b> from the parent folder.<br>
This will create the jar - <b>salestax-zooplus-0.0.1-SNAPSHOT.jar</b> inside the target folder under the parent folder.
<li><h5>Execution</h5>
To run the application, execute the following command from the folder containing the jar file :<br>
<b>java -jar salestax-zooplus-0.0.1-SNAPSHOT.jar</b><br>
OR<br>
If using an IDE, run the main class - <b>SalesTaxApplication.java</b><br>
After the program starts, provide the input and press enter twice to begin computation.</li>
</ul>
<li><h4>Input and Output samples</h4>
<ul>
<li><h5>Test Case 1</h5>
<h6>Input 1:</h6>
1 book at 12.49<br>
1 music CD at 14.99<br>
1 chocolate bar at 0.85<br>
<h6>Output 1:</h6>
1 book : 12.49<br>
1 music CD: 16.49<br>
1 chocolate bar: 0.85<br>
Sales Taxes: 1.50<br>
Total: 29.83<br>
</li>
<li><h5>Test Case 2</h5>
<h6>Input 2:</h6>
1 imported box of chocolates at 10.00<br>
1 imported bottle of perfume at 47.50<br>
<h6>Output 2:</h6>
1 imported box of chocolates: 10.50<br>
1 imported bottle of perfume: 54.65<br>
Sales Taxes: 7.65<br>
Total: 65.15<br></li>
</li>
</ul>