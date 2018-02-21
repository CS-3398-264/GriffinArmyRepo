## 1. Introduction

### 1.1 Purpose 
_<Identify the product whose software requirements are specified in this document, including the revision or release number. Describe the scope of the product that is covered by this SRS, particularly if this SRS describes only part of the system or a single subsystem.>_

### 1.2 Document Conventions
_<Describe any standards or typographical conventions that were followed when writing this SRS, such as fonts or highlighting that have special significance. For example, state whether priorities  for higher-level requirements are assumed to be inherited by detailed requirements, or whether every requirement statement is to have its own priority.>_

### 1.3 Intended Audience and Reading Suggestions
_<Describe the different types of reader that the document is intended for, such as developers, project managers, marketing staff, users, testers, and documentation writers. Describe what the rest of this SRS contains and how it is organized. Suggest a sequence for reading the document, beginning with the overview sections and proceeding through the sections that are most pertinent to each reader type.>_

### 1.4 Product Scope
_<Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.>_

### 1.5 References
_<List any other documents or Web addresses to which this SRS refers. These may include user interface style guides, contracts, standards, system requirements specifications, use case documents, or a vision and scope document. Provide enough information so that the reader could access a copy of each reference, including title, author, version number, date, and source or location.>_

## 2. Overall Description

### 2.1 Product Perspective
_<Describe the context and origin of the product being specified in this SRS. For example, state whether this product is a follow-on member of a product family, a replacement for certain existing systems, or a new, self-contained product. If the SRS defines a component of a larger system, relate the requirements of the larger system to the functionality of this software and identify interfaces between the two. A simple diagram that shows the major components of the overall system, subsystem interconnections, and external interfaces can be helpful.>_

### 2.2 Product Functions
_<Summarize the major functions the product must perform or must let the user perform. Details will be provided in Section 3, so only a high level summary (such as a bullet list) is needed here. Organize the functions to make them understandable to any reader of the SRS. A picture of the major groups of related requirements and how they relate, such as a top level data flow diagram or object class diagram, is often effective.>_

### 2.3 User Classes and Characteristics
_<Identify the various user classes that you anticipate will use this product. User classes may be differentiated based on frequency of use, subset of product functions used, technical expertise, security or privilege levels, educational level, or experience. Describe the pertinent characteristics of each user class. Certain requirements may pertain only to certain user classes. Distinguish the most important user classes for this product from those who are less important to satisfy.>_

### 2.4 Operating Environment
_<Describe the environment in which the software will operate, including the hardware platform, operating system and versions, and any other software components or applications with which it must peacefully coexist.>_

### 2.5 Design and Implementation Constraints
_<Describe any items or issues that will limit the options available to the developers. These might include: corporate or regulatory policies; hardware limitations (timing requirements, memory requirements); interfaces to other applications; specific technologies, tools, and databases to be used; parallel operations; language requirements; communications protocols; security considerations; design conventions or programming standards (for example, if the customer’s organization will be responsible for maintaining the delivered software).>_

### 2.6 User Documentation
_<List the user documentation components (such as user manuals, on-line help, and tutorials) that will be delivered along with the software. Identify any known user documentation delivery formats or standards.>_

### 2.7 Assumptions and Dependencies
_<List any assumed factors (as opposed to known facts) that could affect the requirements stated in the SRS. These could include third-party or commercial components that you plan to use, issues around the development or operating environment, or constraints. The project could be affected if these assumptions are incorrect, are not shared, or change. Also identify any dependencies the project has on external factors, such as software components that you intend to reuse from another project, unless they are already documented elsewhere (for example, in the vision and scope document or the project plan).>_

## 3. External Interface Requirements

### 3.1 User Interfaces
_<Describe the logical characteristics of each interface between the software product and the users. This may include sample screen images, any GUI standards or product family style guides that are to be followed, screen layout constraints, standard buttons and functions (e.g., help) that will appear on every screen, keyboard shortcuts, error message display standards, and so on. Define the software components for which a user interface is needed. Details of the user interface design should be documented in a separate user interface specification.>_

### 3.2 Hardware Interfaces
_<Describe the logical and physical characteristics of each interface between the software product and the hardware components of the system. This may include the supported device types, the nature of the data and control interactions between the software and the hardware, and communication protocols to be used.>_

### 3.3 Software Interfaces
_<Describe the connections between this product and other specific software components (name and version), including databases, operating systems, tools, libraries, and integrated commercial components. Identify the data items or messages coming into the system and going out and describe the purpose of each. Describe the services needed and the nature of communications. Refer to documents that describe detailed application programming interface protocols. Identify data that will be shared across software components. If the data sharing mechanism must be implemented in a specific way (for example, use of a global data area in a multitasking operating system), specify this as an implementation constraint.>_

### 3.4 Communications Interfaces
_<Describe the requirements associated with any communications functions required by this product, including e-mail, web browser, network server communications protocols, electronic forms, and so on. Define any pertinent message formatting. Identify any communication standards that will be used, such as FTP or HTTP. Specify any communication security or encryption issues, data transfer rates, and synchronization mechanisms.>_

## 4. System Features


### 4.1 Greeting User Interface
4.1.1 Description
When the user runs the program, a login interface shall be greeted to create a username for the chess game in the input field. The default username is Guest if no username is entered. 
The interface shall also provide an option button for a second player or computer opponent after the first user name is created. The game of chess shall consist of two different players in the game. 
1. Player / Guest 
2. Computer A.I 


### 4.2 Difficulty level 
4.2.1 Description
The greeting interface should allow the user to change the difficulty level of the A.I opponent. In figure 2, Shows there should be three levels of difficulty, easy, medium, and hard. The default level will be easy. Hard difficulty will maximize the A.I. efficiency to win the game.  

### 4.3 Game Rules / Beginner help FAQ 
4.3.1 Description
As shown in Figure 3, when the user is in the game, a button in the right top corner of the UI window during the game when clicked should open a page with the Griffin Game of Chess rules. The rule FAQ should open a link or list of official chest rules to play Griffin Chess. 


### 4.4 Music Player
4.4.1 Description
While in game is in progress, the user should be able to play music from the embedded music player as shown in Figure 3. The user should not be allowed to add music tracks. The music player will contain the default play/pause, and skip functions for the ease of control of the player.  

### 4.5 Day/Night Mode
4.5.1 Description
The overall U.I theme should be able to change for the comfort of the user due to eyestrain. The default theme when Griffin Chess opens is the day mode U.I. The colors for day mode are visibly brighter for use in bright conditions. Night mode changes the U.I colors to visibly darker colors to decrease the chance of eyestrain. The user should have the option to toggle between either day or night mode while in progress of the game. 

### 4.6 Save Screenshot 
4.6.1 Description
While in progress of the game, the user should have a button to click for a screenshot of the game window. This screenshot could then be downloaded if the user wants. 

### 4.7 Interactive Game Moves
4.7.1 Description
The user should be shown the possible move outcomes with the piece that is chosen. The intergraded A.I should help with the outcomes regardless if there is a human or A.I opponent. The U.I should display a notification on the board to interact with the user. If a move has the outcome of a piece capture, the U.I will display a ‘X’ over the piece that will be captured.



## 5. Other Nonfunctional Requirements

### 5.1 Performance Requirements  
_<If there are performance requirements for the product under various circumstances, state them here and explain their rationale, to help the developers understand the intent and make suitable design choices. Specify the timing relationships for real time systems. Make such requirements as specific as possible. You may need to state performance requirements for individual functional requirements or features.>_

### 5.2 Safety Requirements  
_<Specify those requirements that are concerned with possible loss, damage, or harm that could result from the use of the product. Define any safeguards or actions that must be taken, as well as actions that must be prevented. Refer to any external policies or regulations that state safety issues that affect the product’s design or use. Define any safety certifications that must be satisfied.>_

### 5.3 Security Requirements  
_<Specify any requirements regarding security or privacy issues surrounding use of the product or protection of the data used or created by the product. Define any user identity authentication requirements. Refer to any external policies or regulations containing security issues that affect the product. Define any security or privacy certifications that must be satisfied.>_

### 5.4 Software Quality Attributes  
_<Specify any additional quality characteristics for the product that will be important to either the customers or the developers. Some to consider are: adaptability, availability, correctness, flexibility, interoperability, maintainability, portability, reliability, reusability, robustness, testability, and usability. Write these to be specific, quantitative, and verifiable when possible. At the least, clarify the relative preferences for various attributes, such as ease of use over ease of learning.>_

### 5.5 Business Rules  
_<List any operating principles about the product, such as which individuals or roles can perform which functions under specific circumstances. These are not functional requirements in themselves, but they may imply certain functional requirements to enforce the rules.>_

## 6. Other Requirements  
_<Define any other requirements not covered elsewhere in the SRS. This might include database requirements, internationalization requirements, legal requirements, reuse objectives for the project, and so on. Add any new sections that are pertinent to the project.>_

## Appendix A: Glossary  
_<Define all the terms necessary to properly interpret the SRS, including acronyms and abbreviations. You may wish to build a separate glossary that spans multiple projects or the entire organization, and just include terms specific to a single project in each SRS.>_

## Appendix B: Analysis Models  
_<Optionally, include any pertinent analysis models, such as data flow diagrams, class diagrams, state-transition diagrams, or entity-relationship diagrams.>_  

## Appendix C: To Be Determined List  
_<Collect a numbered list of the TBD (to be determined) references that remain in the SRS so they can be tracked to closure.>_
