# Inventory Management System
This project is a part of `COMP4382, SP.TOP: WEB SERVICES TECHNOLOGY` Course from Birzeit University. which is server based on the OpenAPI 3.1 specification. here i am starting with the design first approach in mind! with i can improve the API in general and expose some of the new features in OAS3.

## Resources

- **Stock** represents the stock in the inventory. It has attributes such as stock ID, name, description, category, cost, selling price, and quantity.

- **Warehouse** represents the locations where inventory stocks are stored or managed. It have attributes such as warehouse ID, name, address, description, capacity

- **Manager** represents the person that manages a single warehouse.

- **Provider** information about the providers(suppliers) whom products are purchased. It has attributes such as provider ID, name, contact information.

### ERD [NEED TO ADD ATTRIBUTES]
![Temp ERD Diagram](https://github.com/albatranomar/inventory-management-system/blob/FirstMilestone/ERD-inventory-management-system.png?raw=true)

### Documentation

<table class="tg">
<thead>
  <tr>
    <th class="tg-c3ow" colspan="6">Collection Resource (/stocks)</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0pky">HTTP request <br>(method URI)</td>
    <td class="tg-0pky">Operation</td>
    <td class="tg-0pky">Description</td>
    <td class="tg-0pky">HTTP status codes</td>
    <td class="tg-0pky">Request sample</td>
    <td class="tg-0pky">Respose sample</td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stocks</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets all stocks in the system</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">GET /stocks</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of stocks]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stock/{id}</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets the stock with given id if exists.</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">GET /stocks/1</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “bag”,<br>price: 4,<br>...<br>...<br>}<br></td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stocksByTag/{tag}</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets all stocks with given tag</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">GET /stocksByTag/reuseable</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of stocks that has the reuseable]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stocksByStatus/{status}</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets all stocks with given status</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">GET /stocksByStatus/available</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of stocks that has the status  available]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0lax">POST /stocks</td>
    <td class="tg-0lax">add</td>
    <td class="tg-0lax">add a new stocks to the system</td>
    <td class="tg-0lax">201 (Created): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0lax">POST /stocks<br>{<br>name: “new stock”,<br><br>price: 4,<br>...<br>...<br><br>}</td>
    <td class="tg-0lax">HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new stock”,<br><br>cost: 4,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0lax">PUT /stocks/{id}</td>
    <td class="tg-0lax">update</td>
    <td class="tg-0lax">update a stock</td>
    <td class="tg-0lax">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0lax">PUT /stocks/1<br>{<br>name: “new name”,<br>}<br></td>
    <td class="tg-0lax">HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>price: 4,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0lax">DELETE /stocks/{id}</td>
    <td class="tg-0lax">delete</td>
    <td class="tg-0lax">deletes a stock from the system</td>
    <td class="tg-0lax">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0lax">DELETE /stocks/1</td>
    <td class="tg-0lax">HTTP/1.1 200<br>OK<br>{<br><br>message: "deleted"<br>}</td>
  </tr>
</tbody>
</table>

<br>

<table class="tg">
<thead>
  <tr>
    <th class="tg-c3ow" colspan="6">Collection Resource (/warehouses)</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0pky">HTTP request <br>(method URI)</td>
    <td class="tg-0pky">Operation</td>
    <td class="tg-0pky">Description</td>
    <td class="tg-0pky">HTTP status codes</td>
    <td class="tg-0pky">Request sample</td>
    <td class="tg-0pky">Respose sample</td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /warehouses</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets all warehouses in the system</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">GET /warehouses</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of warehouses]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">POST /warehouses</td>
    <td class="tg-0pky">add</td>
    <td class="tg-0pky">add a new warehouse to the system</td>
    <td class="tg-0pky">201 (Created): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">POST /warehouses<br>{<br>name: “new warehouse”,<br>...<br>...<br><br>}</td>
    <td class="tg-0pky">HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new warehouse”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">PUT /warehouses/{id}</td>
    <td class="tg-0pky">update</td>
    <td class="tg-0pky">update a warehouse</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">PUT /warehouses/1<br>{<br>name: “new name”,<br>}<br></td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">DELETE /warehouses/{id}</td>
    <td class="tg-0pky">delete</td>
    <td class="tg-0pky">deletes a warehouse from the system</td>
    <td class="tg-0pky">200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td class="tg-0pky">DELETE /warehouses/1</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>message: "deleted"<br><br>}</td>
  </tr>
</tbody>
</table>

<br>

<table>
<thead>
  <tr>
    <th colspan="6">Collection Resource (/managers)</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>HTTP request <br>(method URI)</td>
    <td>Operation</td>
    <td>Description</td>
    <td>HTTP status codes</td>
    <td>Request sample</td>
    <td>Respose sample</td>
  </tr>
  <tr>
    <td>GET /managers</td>
    <td>read</td>
    <td>gets all managers in the system</td>
    <td>200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>GET /managers</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>[list of managers]<br>}</td>
  </tr>
  <tr>
    <td>POST /managers</td>
    <td>add</td>
    <td>add a new manger to the system</td>
    <td>201 (Created): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>POST /managers<br>{<br>name: “new manager”,<br>...<br>...<br><br>}</td>
    <td>HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new manager”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>PUT /managers/{id}</td>
    <td>update</td>
    <td>update a manager</td>
    <td>200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>PUT /managers/1<br>{<br>name: “new name”,<br>}<br></td>
    <td>HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>DELETE /managers/{id}</td>
    <td>delete</td>
    <td>deletes a manager from the system</td>
    <td>200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>DELETE /managers/1</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>message: "deleted"<br>}<br></td>
  </tr>
</tbody>
</table>

<br>

<table>
<thead>
  <tr>
    <th colspan="6">Collection Resource (/providers)</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>HTTP request <br>(method URI)</td>
    <td>Operation</td>
    <td>Description</td>
    <td>HTTP status codes</td>
    <td>Request sample</td>
    <td>Respose sample</td>
  </tr>
  <tr>
    <td>GET /providers</td>
    <td>read</td>
    <td>gets all providers in the system</td>
    <td>200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>GET /providers</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>[list of providers]<br>}</td>
  </tr>
  <tr>
    <td>POST /providers</td>
    <td>add</td>
    <td>add a new provider to the system</td>
    <td>201 (Created): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>POST /providers<br>{<br>name: “new provider”,<br>...<br>...<br><br>}</td>
    <td>HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new provider”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>PUT /providers/{id}</td>
    <td>update</td>
    <td>update a provider</td>
    <td>200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>PUT /providers/1<br>{<br>name: “new name”,<br>}<br></td>
    <td>HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>DELETE /providers/{id}</td>
    <td>delete</td>
    <td>deletes a providersfrom the system</td>
    <td>200 (OK): The request was successful.<br>400 (Bad Request): The server cannot process the request.<br>404 (Not Found): Unavailable endpoint.<br>500 (Internal Server Error): The server encountered an unexpected error.</td>
    <td>DELETE /providers/1</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>message: "deleted"<br>}<br></td>
  </tr>
</tbody>
</table>
