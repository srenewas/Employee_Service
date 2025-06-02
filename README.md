https://.postman.co/workspace/My-Workspace~706b858f-a7db-4d4d-83b1-9a0a2596d07e/collection/7709864-c4bf413c-fd16-40c9-89e3-e388176afec6?action=share&creator=7709864



{
	"info": {
		"_postman_id": "c4bf413c-fd16-40c9-89e3-e388176afec6",
		"name": "Employee",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "7709864"
	},
	"item": [
		{
			"name": "Get_Employee",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/employee/employeeId/119",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee",
						"employeeId",
						"119"
					]
				}
			},
			"response": []
		},
		{
			"name": "Create_Employee",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"id\": \"623184\",\n    \"name\": \"Sujju\",\n    \"department\": \"Java Developer\",\n    \"salary\": 75000.0\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/employee",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete_Employee",
			"request": {
				"method": "DELETE",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/employee/employeeId/623184",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee",
						"employeeId",
						"623184"
					]
				}
			},
			"response": []
		},
		{
			"name": "LB_Employee",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/employee/actuator/health",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee",
						"actuator",
						"health"
					]
				}
			},
			"response": []
		},
		{
			"name": "Patch_Employee",
			"request": {
				"method": "PATCH",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/employee/employeeId/623184",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee",
						"employeeId",
						"623184"
					]
				}
			},
			"response": []
		},
		{
			"name": "Count_Employees",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/employee/allEmployees/count",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee",
						"allEmployees",
						"count"
					]
				}
			},
			"response": []
		},
		{
			"name": "All_Employees",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/employee/allEmployees",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"employee",
						"allEmployees"
					]
				}
			},
			"response": []
		}
	]
}
