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

