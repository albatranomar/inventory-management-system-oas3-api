# Inventory Management System
Server based on the OpenAPI 3.1 specification. With the design first approach in mind! Improve the API in general and expose some of the new features in OAS3.

## Resources

- **Stock** represents the stock in the inventory. It has attributes such as stock ID, name, description, category, cost, selling price, and quantity.

- **Warehouse** represents the locations where inventory stocks are stored or managed. It have attributes such as warehouse ID, name, address, description, capacity

- **Manager** represents the person that manages a single warehouse.

- **Provider** information about the providers(suppliers) whom products are purchased. It has attributes such as provider ID, name, contact information.

### ERD
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
    <td class="tg-0pky">
    200 (OK): The stocks was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to the stocks<br>
    404 (Not Found): Stocks not found.<br>
    410 (Gone): The stocks are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the stocks.<br>
    504 (Timeout): Unable to process the stocks in time
    </td>
    <td class="tg-0pky">GET /stocks</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of stocks]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stocks/{id}</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets the stock with given id if exists.</td>
    <td class="tg-0pky">
    200 (OK): The stock was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to this stock<br>
    404 (Not Found): Stock with spicified id not found.<br>
    410 (Gone): The stock with spicified id are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the stock.<br>
    504 (Timeout): Unable to process the spicified stock in time
    </td>
    <td class="tg-0pky">GET /stocks/1</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “bag”,<br>price: 4,<br>...<br>...<br>}<br></td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stocksByTag/{tag}</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets all stocks with given tag</td>
    <td class="tg-0pky">
    200 (OK): The stocks with spicified tag were successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to spicified stocks<br>
    404 (Not Found): Stocks with spicified tag not found.<br>
    410 (Gone): The stocks with spicified tag are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the stocks.<br>
    504 (Timeout): Unable to process the spicified stocks in time
    </td>
    <td class="tg-0pky">GET /stocksByTag/reuseable</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of stocks that has the reuseable]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">GET /stocksByStatus/{status}</td>
    <td class="tg-0pky">read</td>
    <td class="tg-0pky">gets all stocks with given status</td>
    <td class="tg-0pky">
    200 (OK): The stocks with spicified status were successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to spicified stocks<br>
    404 (Not Found): Stocks with spicified status not found.<br>
    410 (Gone): The stocks with spicified status are no longer available<br>
    500 (Internal Server Error): The server encountered an unexpected error while processing the stocks.<br>
    504 (Timeout): Unable to process the spicified stocks in time
    </td>
    <td class="tg-0pky">GET /stocksByStatus/available</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of stocks that has the status  available]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0lax">POST /stocks</td>
    <td class="tg-0lax">add</td>
    <td class="tg-0lax">add a new stocks to the system</td>
    <td class="tg-0lax">
    201 (Created): The stock was successfully created.<br>
    400 (Bad Request): Invaild stock data provided<br>
    401 (Unauthorized): The client is unauthorized. the stock was not created<br>
    409 (Conflict): The stock provided will conflict the resources on the server<br>
    415 (Unsupported Media Type): Content-type of the request is not supported by this server.<br>
    500 (Internal Server Error): The server encountered an unexpected error while creating the stock.
    </td>
    <td class="tg-0lax">POST /stocks<br>{<br>name: “new stock”,<br><br>price: 4,<br>...<br>...<br><br>}</td>
    <td class="tg-0lax">HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new stock”,<br><br>cost: 4,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0lax">PUT /stocks/{id}</td>
    <td class="tg-0lax">update</td>
    <td class="tg-0lax">update a stock</td>
    <td class="tg-0lax">
    200 (OK): The stock was successfully updated.<br>
    400 (Bad Request): Invalid stock id<br>
    401 (Unauthorized): The client is unauthorized. the stock was not updated<br>
    404 (Not Found): Stock not found.<br>
    409 (Conflict): The new stock data provided will conflict the resources on the server<br>
    500 (Internal Server Error): The server encountered an unexpected error while updating the stock.
    </td>
    <td class="tg-0lax">PUT /stocks/1<br>{<br>name: “new name”,<br>}<br></td>
    <td class="tg-0lax">HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>price: 4,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0lax">DELETE /stocks/{id}</td>
    <td class="tg-0lax">delete</td>
    <td class="tg-0lax">deletes a stock from the system</td>
    <td class="tg-0lax">
    200 (OK): The stock was successfully deleted.<br>
    400 (Bad Request): Invalid stock id<br>
    401 (Unauthorized): The client is unauthorized. the stock was not deleted<br>
    403 (Forbidden): The client doesn't have access to spicified stock. Was unable to deleted<br>
    404 (Not Found): Stock not found.<br>
    500 (Internal Server Error): The server encountered an unexpected error while deleting the stock.
    </td>
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
    <td class="tg-0pky">
    200 (OK): The warehouses was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to the warehouses<br>
    404 (Not Found): Warehouses not found.<br>
    410 (Gone): The warehouses are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the warehouses.<br>
    504 (Timeout): Unable to process the warehouses in time
    </td>
    <td class="tg-0pky">GET /warehouses</td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>[list of warehouses]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">POST /warehouses</td>
    <td class="tg-0pky">add</td>
    <td class="tg-0pky">add a new warehouse to the system</td>
    <td class="tg-0pky">
    201 (Created): The warehouse was successfully created.<br>
    400 (Bad Request): Invaild warehouse data provided<br>
    401 (Unauthorized): The client is unauthorized. the warehouse was not created<br>
    409 (Conflict): The warehouse provided will conflict the resources on the server<br>
    415 (Unsupported Media Type): Content-type of the request is not supported by this server.<br>
    500 (Internal Server Error): The server encountered an unexpected error while creating the warehouse.
    </td>
    <td class="tg-0pky">POST /warehouses<br>{<br>name: “new warehouse”,<br>...<br>...<br><br>}</td>
    <td class="tg-0pky">HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new warehouse”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>GET /warehouses/{id}</td>
    <td>read</td>
    <td>gets a single warehouse in the system</td>
    <td>
    200 (OK): The warehouse was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to this warehouse<br>
    404 (Not Found): Warehouse with spicified id not found.<br>
    410 (Gone): The warehouse with spicified id are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the warehouse.<br>
    504 (Timeout): Unable to process the spicified warehouse in time
    </td>
    <td>GET /warehouses/1</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “warehouse 1”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>GET /warehouses/{id}/stocks</td>
    <td>read</td>
    <td>Gets a all stocks in the given warehouse by id</td>
    <td>
    200 (OK): The stocks was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to the stocks<br>
    404 (Not Found): Stocks not found.<br>
    410 (Gone): The stocks are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the stocks.<br>
    504 (Timeout): Unable to process the stocks in time
    </td>
    <td>GET /warehouses/1/stocks</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>[list of stocks]<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">PUT /warehouses/{id}</td>
    <td class="tg-0pky">update</td>
    <td class="tg-0pky">update a warehouse</td>
    <td class="tg-0pky">
    200 (OK): The warehouse was successfully updated.<br>
    400 (Bad Request): Invalid warehouse id<br>
    401 (Unauthorized): The client is unauthorized. the warehouse was not updated<br>
    404 (Not Found): Warehouse with spicified id not found.<br>
    409 (Conflict): The new warehouse data provided will conflict the resources on the server<br>
    500 (Internal Server Error): The server encountered an unexpected error while updating the warehouse.
    </td>
    <td class="tg-0pky">PUT /warehouses/1<br>{<br>name: “new name”,<br>}<br></td>
    <td class="tg-0pky">HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td class="tg-0pky">DELETE /warehouses/{id}</td>
    <td class="tg-0pky">delete</td>
    <td class="tg-0pky">deletes a warehouse from the system</td>
    <td class="tg-0pky">
    200 (OK): The warehouse was successfully deleted.<br>
    400 (Bad Request): Invalid warehouse id<br>
    401 (Unauthorized): The client is unauthorized. the warehouse was not deleted<br>
    403 (Forbidden): The client doesn't have access to spicified warehouse. Was unable to deleted<br>
    404 (Not Found): warehouse with spicified id not found.<br>
    500 (Internal Server Error): The server encountered an unexpected error while deleting the warehouse.</td>
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
    <td>
    200 (OK): The managers were successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to the managers<br>
    404 (Not Found): Managers not found.<br>
    410 (Gone): The managers are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the managers.<br>
    504 (Timeout): Unable to process the managers in time
    </td>
    <td>GET /managers</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>[list of managers]<br>}</td>
  </tr>
  <tr>
    <td>GET /managers/{id}</td>
    <td>read</td>
    <td>gets a single manager in the system</td>
    <td>
    200 (OK): The manager was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to this manager<br>
    404 (Not Found): Manager with spicified id not found.<br>
    410 (Gone): The manager with spicified id are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the manager.<br>
    504 (Timeout): Unable to process the spicified manager in time
    </td>
    <td>GET /managers/1</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>id: “1”,<br>name: “manager1”,<br>...<br>...<br><br>}</td>
  </tr>
  <tr>
    <td>POST /managers</td>
    <td>add</td>
    <td>add a new manger to the system</td>
    <td>
    201 (Created): The manager was successfully created.<br>
    400 (Bad Request): Invaild manager data provided<br>
    401 (Unauthorized): The client is unauthorized. the manager was not created<br>
    409 (Conflict): The manager provided will conflict the resources on the server<br>
    415 (Unsupported Media Type): Content-type of the request is not supported by this server.<br>
    500 (Internal Server Error): The server encountered an unexpected error while creating the manager.
    </td>
    <td>POST /managers<br>{<br>name: “new manager”,<br>...<br>...<br><br>}</td>
    <td>HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new manager”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>PUT /managers/{id}</td>
    <td>update</td>
    <td>update a manager</td>
    <td>
    200 (OK): The manager was successfully updated.<br>
    400 (Bad Request): Invalid manager id<br>
    401 (Unauthorized): The client is unauthorized. the manager was not updated<br>
    404 (Not Found): Manager with spicified id not found.<br>
    409 (Conflict): The new manager data provided will conflict the resources on the server<br>
    500 (Internal Server Error): The server encountered an unexpected error while updating the manager.
    </td>
    <td>PUT /managers/1<br>{<br>name: “new name”,<br>}<br></td>
    <td>HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>DELETE /managers/{id}</td>
    <td>delete</td>
    <td>deletes a manager from the system</td>
    <td>
    200 (OK): The manager was successfully deleted.<br>
    400 (Bad Request): Invalid manager id<br>
    401 (Unauthorized): The client is unauthorized. the manager was not deleted<br>
    403 (Forbidden): The client doesn't have access to spicified manager. Was unable to deleted<br>
    404 (Not Found): Manager with spicified id not found.<br>
    500 (Internal Server Error): The server encountered an unexpected error while deleting the manager.
    </td>
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
    <td>
    200 (OK): The providers were successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to the providers<br>
    404 (Not Found): Providers not found.<br>
    410 (Gone): The providers are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the providers.<br>
    504 (Timeout): Unable to process the providers in time
    </td>
    <td>GET /providers</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>[list of providers]<br>}</td>
  </tr>
  <tr>
    <td>GET /providers/{id}</td>
    <td>read</td>
    <td>gets a single provider in the system</td>
    <td>
    200 (OK): The provider was successfully acquired.<br>
    403 (Forbidden): The client doesn't have access to this provider<br>
    404 (Not Found): Provider with spicified id not found.<br>
    410 (Gone): The provider with spicified id are no longer available<br>
    500 (Internal Server Error): The server encountered while processing the provider.<br>
    504 (Timeout): Unable to process the spicified provider in time
    </td>
    <td>GET /providers</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>[list of providers]<br>}</td>
  </tr>
  <tr>
    <td>POST /providers</td>
    <td>add</td>
    <td>add a new provider to the system</td>
    <td>
    201 (Created): The provider was successfully created.<br>
    400 (Bad Request): Invaild data for the provider<br>
    401 (Unauthorized): The client is unauthorized. the provider was not created<br>
    409 (Conflict): The given provider will conflict the resources on the server<br>
    415 (Unsupported Media Type): Content-type of the request is not supported by this server.<br>
    500 (Internal Server Error): The server encountered an unexpected error while creating the provider.
    </td>
    <td>POST /providers<br>{<br>name: “new provider”,<br>...<br>...<br><br>}</td>
    <td>HTTP/1.1 201<br>Created<br>{<br>id: 2445<br>name: “new provider”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>PUT /providers/{id}</td>
    <td>update</td>
    <td>update a provider</td>
    <td>
    200 (OK): The provider was successfully updated.<br>
    400 (Bad Request): Invalid provider id<br>
    401 (Unauthorized): The client is unauthorized. the provider was not updated<br>
    404 (Not Found): Provider with spicified id not found.<br>
    409 (Conflict): The new provider data will conflict the resources on the server<br>
    500 (Internal Server Error): The server encountered an unexpected error while updating the provider.
    </td>
    <td>PUT /providers/1<br>{<br>name: “new name”,<br>}<br></td>
    <td>HTTP/1.1 200<br>OK<br>{<br>id: 1<br>name: “new name”,<br>...<br>...<br>}</td>
  </tr>
  <tr>
    <td>DELETE /providers/{id}</td>
    <td>delete</td>
    <td>deletes a providersfrom the system</td>
    <td>
    200 (OK): The provider was successfully deleted.<br>
    400 (Bad Request): Invalid provider id<br>
    401 (Unauthorized): The client is unauthorized. the provider was not deleted<br>
    403 (Forbidden): The client doesn't have access to spicified provider. Was unable to deleted<br>
    404 (Not Found): Provider with spicified id not found.<br>
    500 (Internal Server Error): The server encountered an unexpected error while deleting the provider.
    </td>
    <td>DELETE /providers/1</td>
    <td>HTTP/1.1 200<br>OK<br>{<br>message: "deleted"<br>}<br></td>
  </tr>
</tbody>
</table>


### OpenAPI Specification Swagger
You can access the specification by following the link [Inventory Management System](https://app.swaggerhub.com/apis/ALBATRANOMAR943/inventory-management-system/1.0.0)
or head to 'inventory-management-system-openapi3_1.yaml' in this repo
