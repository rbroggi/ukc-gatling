# Performance tester for UKC

This is a simple scala-maven project which makes use of [Gatling](https://gatling.io/) 
to load-test the [UKC](https://www.unboundtech.com/unbound-key-control/) product.

So far only the below mentioned simulations are available: 

1. EcdsaSimulation
   * endopoint used: https://<your_ip_port>/api/v1/keys/\<keyId\>/sign?partition=\<partitionName\>
1. SymmetricEncryptionSimulation
   * endopoint used: https://<your_ip_port>/api/v1/keys/\<keyId\>/encrypt?partition=\<partitionName\>
1. HmacSimulation
   * endopoints used: 
     1. https://<your_ip_port>/api/v1/keys/\<keyId\>/mac?partition=\<partitionName\>
     1. https://<your_ip_port>/api/v1/keys/\<keyId\>/macVerify?partition=\<partitionName\>
1. SymmetricEncryptionDecryptionSimulation
    * endopoints used:
        1. https://<your_ip_port>/api/v1/keys/\<keyId\>/encrypt?partition=\<partitionName\>
        1. https://<your_ip_port>/api/v1/keys/\<keyId\>/decrypt?partition=\<partitionName\>
   
    
All the parameters for a simulation can be found in the `src/test/scala/com/amadeus/config/Config.scala` file. 
They can be passed as commandline arguments as in the example below.

1. **ipPort** "ip:port" string 
1. **key** key to be targeted for the operation 
1. **partition** to be targeted
1. **basicAuth** is a basic HTTP authentication to be provided as base64("user@partition:password")
1. **bearerAuth** is a bearer HTTP authentication to be provided as base64 string. This one will be prefered over basicAuth
1. **duration** of the simulation in minutes 
1. **payloadSize** size of the payload in bytes in which the operations will be done


To run a simulation use the following command
```bash
mvn gatling:test -Dgatling.simulationClass=com.amadeus.simulations.<nameSimulation> -DipPort="ip:port" -Dkey="<keyName>" -Dpartition="<partitionName>" -Dauth="<basicHTTPAuthentication>" -Dduration=<simulationDuration> -DpayloadSize=<payloadSize> 
```

Example for :
1. using the SymmetricEncryptionDecryptionSimulation simulation (encrypts and decrypts using AES GCM )
1. shooting on localhost port 8443
1. with a key named "symKey"
1. with a partition named "nicePartition"
1. with authentication details:
   1. user "niceUser"
   1. partition "nicePartition"
   1. password "verySecure"
   1. which leads to the following string "niceUser@nicePartition:verySecure" that based64 becomes: "bmljZVVzZXJAbmljZVBhcnRpdGlvbjp2ZXJ5U2VjdXJl"
1. for 5 minutes
1. using payloads of 4000bytes (4kB)

```bash
mvn gatling:test -Dgatling.simulationClass=com.amadeus.simulations.SymmetricEncryptionDecryptionSimulation -DipPort="localhost:8443" -Dkey="symKey" -Dpartition="nicePartition" -Dauth="bmljZVVzZXJAbmljZVBhcnRpdGlvbjp2ZXJ5U2VjdXJl" -Dduration=5 -DpayloadSize=4000
```
