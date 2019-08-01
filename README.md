# Joint Military Symbology Java

An experimental [MIL-STD-2525D](http://www.assistdocs.com/search/document_details.cfm?ident_number=114934)/[APP-6](http://en.wikipedia.org/wiki/NATO_Military_Symbols_for_Land_Based_Systems) symbology Java library based on the [ESRI's Joint Military Symbology Markup Language (JMSML)](https://github.com/Esri/joint-military-symbology-xml) 
project.

![Sample Symbol](docs/sample-symbol.PNG "Title")

How it works
------------

The library uses Apache Batik to generate SVg symbols based on


This project is a work in progress. 

Getting started
---------------

    $ git clone https://github.com/yemikudaisi/joint-military-symbology-java.git

Usage
-----
### Basic
To create a basic military symbol

```java
MilitarySymbol milSym = new MilitarySymbol();
milSym.setStandardEntityOne(StandardEntityOnes.Reality);
milSym.setStandardEntityTwo(StandardEntityTwos.Friend);
milSym.setSymbolSet(SymbolSets.LandUnits);
milSym.setStatusAmplifier(Status.Present);
milSym.setStatusAmplifierMode(StatusAmplifierModes.Default);
milSym.setHqTFDummy(HQTFDummy.NotApplicable);
milSym.setAmplifier(EchelonAmplifier.Battalion);
milSym.setEntity(new Entity("11","130000"));
milSym.setEntityType(new EntityType("10","111100"));
milSym.setEntitySubType(new EntitySubType("00","111100"));
milSym.setSectorOneModifier(new Modifier("17","17"));
milSym.setSectorTwoModifier(new Modifier("12","17"));
System.out.println(milSym);
    
   //Prints the SIDC code for the symbol -> 10031000161110001712
```


This library provides an alternate Status/OCA modifier with is based on colored bars. To use alternate mode:

```java
milSym.setStatusAmplifierMode(StatusAmplifierModes.Default);
```

### Amplifiers (Echelon\Equipment Mobility\ Naval Towed Arrays)
Since some amplifiers do not apply to some symbol sets (for example a Naval towed array amplifier cannot be applied to a Land unit symbol set). To dynamically get an array of amplifiers for a symbol set (e.g land units) use:

```java
Amplifier[] amplifiers = MilitarySymbolFactory.getApplicableAmplifiers(SymbolSets.LandUnits)

// returns EchelonAmplifiers.values()
```

To check if an amplifier is applicable to a symbol set:

```java
boolean isApplicable = MilitarySymbolFactory.isAmplifierApplicable(SymbolSets.LandUnits, EchelonAmplifier.Platoon)
// returns true
```

### Enities\Entity types\Entity subtype

The lis capable of generating a Tree of Entites, types, sub types and modifiers for a given symbols set.
To access this use:
```
SymbolSetEntityModifierTree h = MilitarySymbolFactory.createSymbolSetEntityModifierTree(SymbolSets.LandUnits);
```
Output
```
->Land Units
# Entities -> Types -> Subtypes: 
--->Unspecified - 000000
--->Command and Control - 110000
----->Broadcast Transmitter Antennae - 110100
----->Civil Affairs - 110200
----->Civil-Military Cooperation - 110300
----->Information Operations - 110400
----->Liaison - 110500
----->Military Information Support (MISO) - 110600
-------->Broadcast Transmitter Antennae - 110601
----->Radio - 110700
----->Radio Relay - 110800
----->Radio Teletype Center - 110900
----->Signal - 111000
-------->Radio - 111001
-------->Radio Relay - 111002
-------->Teletype - 111003
-------->Tactical Satellite - 111004
-------->Video Imagery (Combat Camera) - 111005
----->Tactical Satellite - 111100
----->Video Imagery (Combat Camera) - 111200
----->Air Assault with Organic Lift - 120100
----->Air Traffic Services/Airfield Operations - 120200
----->Amphibious - 120300
----->Antitank/Antiarmor - 120400
-------->Armored - 120401
-------->Motorized - 120402
----->Armor/Armored/Mechanized/Self-Propelled/Tracked - 120500
-------->Reconnaissance/Cavalry/Scout - 120501
-------->Amphibious - 120502
----->Army Aviation/Aviation Rotary Wing - 120600
-------->Reconnaissance - 120601
----->Aviation Composite - 120700
----->Aviation Fixed Wing - 120800
-------->Reconnaissance - 120801
----->Combat - 120900
----->Combined Arms - 121000
----->Infantry - 121100
-------->Amphibious - 121101
-------->Armored/Mechanized/Tracked - 121102
-------->Main Gun System - 121103
-------->Motorized - 121104
-------->Infantry Fighting Vehicle - 121105
----->Observer - 121200
----->Reconnaissance/Cavalry/Scout - 121300
-------->Reconnaissance and Surveillance - 121301
-------->Marine - 121302
-------->Motorized - 121303
----->Sea Air Land (SEAL) - 121400
----->Sniper - 121500
----->Surveillance - 121600
----->Special Forces - 121700
----->SOF - 121800
-------->Fixed Wing MISO - 121801
-------->Ground - 121802
-------->Special Boat - 121803
-------->Special SSNR - 121804
-------->Underwater Demolition Team - 121805
----->Unmanned Aerial Systems - 121900
----->Air Defense - 130100
-------->Main Gun System - 130101
-------->Missile - 130102
----->Air/Land Naval Gunfire Liaison - 130200
----->Field Artillery - 130300
-------->Self-propelled - 130301
-------->Target Acquisition - 130302
----->Field Artillery Observer - 130400
----->Joint Fire Support - 130500
----->Meteorological - 130600
----->Missile - 130700
----->Mortar - 130800
-------->Armored/Mechanized/Tracked - 130801
-------->Self-Propelled Wheeled - 130802
-------->Towed - 130803
----->Survey - 130900
----->CBRN Defense - 140100
-------->Mechanized - 140101
-------->Motorized - 140102
-------->Reconnaissance - 140103
-------->Reconnaissance Armored - 140104
-------->Reconnaissance Equipped - 140105
----->Combat Support (Maneuver Enhancement) - 140200
----->Criminal Investigation Division - 140300
----->Diving - 140400
----->Dog - 140500
----->Drilling - 140600
----->Engineer - 140700
-------->Mechanized - 140701
-------->Motorized - 140702
-------->Reconnaissance - 140703
----->Explosive Ordnance Disposal (EOD) - 140800
----->Field Camp Construction - 140900
----->Fire Fighting/Fire Protection - 141000
----->Geospatial Support/Geospatial Information Support - 141100
----->Military Police - 141200
----->Mine - 141300
----->Mine Clearing - 141400
----->Mine Launching - 141500
----->Mine Laying - 141600
----->Security - 141700
-------->Mechanized - 141701
-------->Motorized - 141702
----->Search and Rescue - 141800
----->Security Police (Air) - 141900
----->Shore Patrol - 142000
----->Topographic - 142100
----->Analysis - 150100
----->Counterintelligence - 150200
----->Direction Finding - 150300
----->Electronic Ranging - 150400
----->Electronic Warfare - 150500
-------->Analysis - 150501
-------->Direction Finding - 150502
-------->Intercept - 150503
-------->Jamming - 150504
-------->Search - 150505
----->Intercept (Search and Recording) - 150600
----->Interrogation - 150700
----->Jamming - 150800
----->Joint Intelligence Center - 150900
----->Military Intelligence - 151000
----->Search - 151100
----->Sensor - 151200
--->Sustainment - 160000
----->Administrative - 160100
----->All Classes of Supply - 160200
----->Airport of Debarkation/Airport of Embarkation - 160300
----->Ammunition - 160400
----->Band - 160500
----->Combat Service Support - 160600
----->Finance - 160700
----->Judge Advocate General - 160800
----->Labor - 160900
----->Laundry/Bath - 161000
----->Maintenance - 161100
----->Material - 161200
----->Medical - 161300
----->Medical Treatment Facility - 161400
----->Morale Welfare and Recreation - 161500
----->Mortuary Affairs/Graves Registration - 161600
----->Multiple Classes of Supply - 161700
----->NATO Supply Class I - 161800
----->NATO Supply Class II - 161900
----->NATO Supply Class III - 162000
----->NATO Supply Class IV - 162100
----->NATO Supply Class V - 162200
----->Ordnance - 162300
----->Personnel Services - 162400
----->Petroleum Oil and Lubricants - 162500
----->Pipeline - 162600
----->Postal - 162700
----->Public Affairs/Public Information - 162800
----->Quartermaster - 162900
----->Railhead - 163000
----->Religious Support - 163100
----->Replacement Holding Unit - 163200
----->Sea Port of Debarkation/Sea Port of Embarkation - 163300
----->Supply - 163400
----->Joint Information Bureau - 163500
----->Transportation - 163600
----->US Supply Class I - 163700
----->US Supply Class II - 163800
----->US Supply Class III - 163900
----->US Supply Class IV - 164000
----->US Supply Class V - 164100
----->US Supply Class VI - 164200
----->US Supply Class VII - 164300
----->US Supply Class VIII - 164400
----->US Supply Class IX - 164500
----->US Supply Class X - 164600
----->Water - 164700
----->Water Purification - 164800
----->Broadcast - 164900
----->Naval - 170100
----->Allied Command Europe Rapid Reaction Corps (ARRC) - 180100
----->Allied Command Operations - 180200
----->International Security Assistance Force (ISAF) - 180300
----->Multinational (MN) - 180400
--->Emergency Operation (Land Units) - 190000
--->Law Enforcement (Land Units) - 200000
----->ATF DOJ - 200100
----->Border Patrol - 200200
----->Customs Service - 200300
----->DEA - 200400
----->DOJ - 200500
----->FBI - 200600
----->Police - 200700
----->Prison - 200800
----->US Secret Service (USSS) - 200900
----->TSA - 201000
----->Coast Guard - 201100
----->US Marshals Service - 201200
----->Internal Security Force - 201300

# Modifier One: 
-->Unspecified - 00
-->Mobility : Air Mobile/Air Assault (US Only) - 01
-->Capability : Area - 02
-->Capability : Attack 1 - 03
-->Capability : Biological - 04
-->Capability : Border - 05
-->Capability : Bridging - 06
-->Capability : Chemical - 07
-->Capability : Close Protection - 08
-->Capability : Combat - 09
-->Capability : Command and Control - 10
-->Capability : Communications Contingency Package - 11
-->Capability : Construction - 12
-->Capability : Cross Cultural Communication - 13
-->Capability : Crowd and Riot Control - 14
-->Capability : Decontamination 1 - 15
-->Capability : Detention - 16
-->Capability : Direct Communications - 17
-->Capability : Diving - 18
-->Capability : Division - 19
-->Capability : Dog - 20
-->Capability : Drilling - 21
-->Capability : Electro-Optical - 22
-->Capability : Enhanced - 23
-->Capability : Explosive Ordnance Disposal (EOD) - 24
-->Capability : Fire Direction Center - 25
-->Capability : Force - 26
-->Capability : Forward - 27
-->Capability : Ground Station Module - 28
-->Capability : Landing Support - 29
-->Capability : Large Extension Node - 30
-->Capability : Maintenance - 31
-->Capability : Meteorological - 32
-->Capability : Mine Countermeasure - 33
-->Capability : Missile - 34
-->Capability : Mobile Advisor and Support - 35
-->Capability : Mobile Subscriber Equipment - 36
-->Capability : Mobility Support - 37
-->Capability : Movement Control Center - 38
-->Capability : Multinational - 39
-->Capability : Multinational Specialized Unit - 40
-->Capability : Multiple Rocket Launcher - 41
-->Capability : NATO Medical Role 1 - 42
-->Capability : NATO Medical Role 2 - 43
-->Capability : NATO Medical Role 3 - 44
-->Capability : NATO Medical Role 4 - 45
-->Capability : Naval - 46
-->Capability : Node Center - 47
-->Capability : Nuclear - 48
-->Capability : Operations - 49
-->Capability : Radar - 50
-->Capability : RFID Interrogator/Sensor - 51
-->Capability : Radiological - 52
-->Capability : Search and Rescue - 53
-->Capability : Security - 54
-->Capability : Sensor - 55
-->Capability : Sensor Control Module (SCM) - 56
-->Capability : Signals Intelligence - 57
-->Capability : Single Shelter Switch - 58
-->Capability : Single Rocket Launcher - 59
-->Capability : Smoke - 60
-->Capability : Sniper - 61
-->Capability : Sound Ranging - 62
-->Capability : Special Operations Forces (SOF) - 63
-->Capability : Special Weapons and Tactics - 64
-->Capability : Survey - 65
-->Capability : Tactical Exploitation - 66
-->Capability : Target Acquisition - 67
-->Capability : Topographic - 68
-->Capability : Utility 1 - 69
-->Capability : Video Imagery (Combat Camera) - 70
-->Composite Loss : Accident (Land Units) - 71
-->Composite Loss : Other (Land Units) - 72
-->Operation : Civilian (Land Units) - 73
-->Capability : Antisubmarine Warfare - 74
-->Capability : Medevac - 75
-->Capability : Ranger - 76
-->Capability : Support 1 - 77
-->Capability : Aviation - 78
# Modifier Two: 
-->Unspecified - 00
-->Mobility : Airborne - 01
-->Mobility : Arctic - 02
-->Capability : Battle Damage Repair - 03
-->Mobility : Bicycle Equipped - 04
-->Close Range and Support : Casualty Staging - 05
-->Capability : Clearing - 06
-->Capability : Close Range - 07
-->Capability : Control - 08
-->Capability : Decontamination 2 - 09
-->Capability : Demolition - 10
-->Capability : Dental - 11
-->Capability : Digital - 12
-->Capability : Enhanced Position Location Reporting System (EPLRS) - 13
-->Capability : Equipment - 14
-->Capability : Heavy - 15
-->Capability : High Altitude - 16
-->Capability : Intermodal - 17
-->Capability : Intensive Care - 18
-->Capability : Light - 19
-->Capability : Laboratory - 20
-->Capability : Launcher - 21
-->Capability : Long Range - 22
-->Capability : Low Altitude - 23
-->Capability : Medium - 24
-->Capability : Medium Altitude - 25
-->Capability : Medium Range - 26
-->Capability : Mountain - 27
-->Capability : High to Medium Altitude - 28
-->Capability : Multi-Channel - 29
-->Capability : Optical (Flash) - 30
-->Capability : Pack Animal - 31
-->Capability : Patient Evacuation Coordination - 32
-->Capability : Preventive Maintenance - 33
-->Capability : Psychological - 34
-->Capability : Radio Relay Line of Sight - 35
-->Mobility : Railroad - 36
-->Capability : Recovery (Unmanned Systems) - 37
-->Capability : Recovery (Maintenance) - 38
-->Capability : Rescue Coordination Center - 39
-->Mobility : Riverine - 40
-->Capability : Single Channel - 41
-->Mobility : Ski - 42
-->Capability : Short Range - 43
-->Capability : Strategic - 44
-->Capability : Support 2 - 45
-->Capability : Tactical - 46
-->Mobility : Towed - 47
-->Capability : Troop - 48
-->Mobility : Vertical or Short Take-Off and Landing (VTOL/VSTOL) - 49
-->Capability : Veterinary - 50
-->Mobility : Wheeled - 51
-->Capability : High to Low Altitude - 52
-->Capability : Medium to Low Altitude - 53
-->Capability : Attack 2 - 54
-->Capability : Refuel - 55
-->Capability : Utility 2 - 56
-->Capability : Combat Search and Rescue - 57
```
