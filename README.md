# Weather Forecast


# Architecture used - MVVM
This application makes use of the MVVM architecture for development purpose.
Dagger is used for dependency injection.
Rx Java along with Retrofit is used for making the API calls/Networking layer.
Live data is used for following an observable pattern to notify the UI of any changes
Navigation component is used for building a single Activity architecture. 
Google Play services, Fused Location Service API is used for requesting the location.  
Kotlin safe args plugins (Type safety) is used for safely passing arguments between different fragments.

# Future work

Non - Functional improvements
Use of Data binding for the UI 
Use of Room database SQL Lite for persistence 
Use of GCM Network Manager for managing network calls in terms of retires, exponential back offs, batching
Adapting to latency

Functional improvements
Use of card view to display various components.
Providing user the option for selecting location.
Providing user the option for switching the metric for temperature and other fields.
Images for location, day/night, wind and other fields.

`<p align="center">
  <img src="https://user-images.githubusercontent.com/24234056/133220823-36209953-4414-4915-8758-86a7648569a1.jpg" width="350">
  <img src="https://user-images.githubusercontent.com/24234056/133220828-d62e4a7e-a79c-488a-b144-a15d1c3acb4c.jpg" width="350">
  <img src="https://user-images.githubusercontent.com/24234056/133220830-e1f88e76-a281-48d9-8e7f-e43ab283649a.jpg" width="350">
  <img src="https://user-images.githubusercontent.com/24234056/133220836-3e8b51b4-bf78-4d61-bf4b-c029d328fce2.jpg" width="350">
</p>
