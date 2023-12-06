# Encryption Algorithms GUI <sub>CSC429 - Computer Security Project</sub>
## Table of contents
- [Introduction ](#Introduction)
- [Importance of the Platform](#Importance-of-the-Platform)
- [Interface Overview](#Interface-Overview)
- [Download Project Guide](#Download-Project-Guide)
- [Tips for editing code](#Tips-for-editing-code)
  - [How to add your algorithm to the drop-menu list](#How-to-add-your-algorithm-to-the-drop-menu-list)
  - [How to get key values](#How-to-get-key-values)
  - [How to get User input text values](#How-to-get-User-input-text-values)
  - [Display error](#Display-error)
  - [Display results](#Display-results)
  - [Link your algorithm to encrypt and decrypt buttons](#Link-your-algorithm-to-encrypt-and-decrypt-buttons)
  - [HMAC - Digital Signature - Hash](#HMAC---Digital-Signature---Hash)
- [Upload Project Guide](#Upload-Project-Guide)
- [Community support (algorithms uploaded)](#Community-support)
- [Team Members](#Team-Members)
  
## Introduction
In an era dominated by digital communication and sensitive data exchange, the significance of robust cryptographic solutions cannot be overstated. 
As the demand for secure information transfer grows, the need for a unified platform integrating various cryptographic algorithms becomes increasingly paramount. This Repository introduces an open-source project – a Graphic User Interface (GUI) platform built through a Java application using the WindowBuilder tool 
 that amalgamates diverse cryptographic algorithms such as RSA, AES, and other related operations, implemented by contributors, contributors can contribute by uploading their encryption algorithms through GitHub. 


## Importance of the Platform

- Versatility and Interoperability:
    Our platform addresses the challenge of managing multiple cryptographic algorithms by providing a centralized interface, allowing users to seamlessly navigate through various protocols. This versatility enhances interoperability across different systems and applications.

- Security Enhancement:
   By consolidating a spectrum of cryptographic algorithms, the platform empowers users with the ability to select and combine protocols strategically.
  This not only fortifies the security of sensitive data but also allows for the adaptation of encryption strategies based on specific use cases and evolving       
  threats.

- User-Friendly Experience:
    The GUI design of the platform ensures that even users with limited cryptographic expertise can harness the power of advanced algorithms effortlessly.
  This democratization of cryptographic tools fosters wider adoption and promotes secure practices across diverse user profiles.
  
- Community Support
	By creating an open-source project, we can build a community of like-minded individuals who share our passion for security. This can lead to new opportunities for collaboration, learning, and growth.

 
## Tools used
  - Java programming language
  - `Window builder` <sub> Java GUI designer tool that allows you to create Java GUI applications without writing any code </sub>
  - `Eclipse IDE`

## Interface Overview

Our Graphic User Interface (GUI) has been meticulously crafted to provide users with an effortless and comprehensive experience in managing cryptographic operations. The platform is structured around a straightforward process, As follows.


- Algorithm Selection & Brief about the algorithm

- The ability to open a file

- Keys fields Management

- User input (for Encryption and decryption) 

- Encrypt & Decrypt buttons

- HASH & Digital signature & HMAC buttons

- Result Display
  
- The ability to save results into a file

## Download Project Guide
In this section, you will learn How To get your Own Copy of the Code
1. Login or create a new `Github` Account
2. In the top right corner Click the `Fork` Button
   
![Screenshot 2023-11-13 215108](https://github.com/abdullahAlsaab/Encryption-Algorithms-GUI/assets/97760612/dcf0848f-1fc6-49ed-88e6-6819d5b39207)

3. Make sure that the `Copy the main branch only` button is selected, then Click the `Create Fork` Button
   
![Screenshot 2023-11-13 215238](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/95296f97-e8d6-4378-ad1c-092a48c7b407)


Now you have your Version Of the Code in your account, below are the steps of how to `Clone` the files into your computer

> [!NOTE]
>There are several ways of `cloning` the repository, follow the steps for one of them


![Screenshot 2023-11-13 215404](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/8a02d105-a223-4ae5-aed9-20798f2576e6)

![Screenshot 2023-11-13 220133](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/8b1d129a-38df-4244-babe-74936706f14a)

![Screenshot 2023-11-13 220147](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/142e6576-5cff-4489-a30c-42ee61b6d4ad)

![Screenshot 2023-11-13 220236](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/53acc808-415b-422f-9b67-79544a0f1dfd)



4. Add your Changes To the Code, To Know How to Add your Algorithm Click [Tips for editing code](#Tips-for-editing-code).

5. After you finish the code, upload the project and merge it with ours, to know how click [here](#Upload-Project-Guide).
   

## Tips for editing code

### How to add your algorithm to the drop-menu list
> [!TIP]
>Put your algorithm brief inside HTML brackets for a better user experience, as shown following :point_right: ,
> \<HTML> Example brief \</HTML>
```Java
Object[][] ArrayOfAlgorithms = {
	{ "Example Algorithm", }, // Algorithm name, String
	{ "Example Brief", }, // Algorithm brief (also write the required format of writing the keys), String
	{ 1, } // Maximum number of keys of your algorithm, Integer
};
```
### How to get key values
```Java
getKeyValue() // Call this method, it will return an array of key values
```

### How to get User input text values
```Java
String plain_text = inputtext.getText() // now you stored user input into plain_text variable
```

### Display error
> [!TIP]
>Try to show errors in all the scenarios, when the user types the Text/key in the wrong format, or when something unpredictable happened, and so on.

```java
Error("Add here the text of your error") // Just call the Error() function with your message as a parameter and it will be displayed to the user
```
### Display results
```Java
results.setText("Write here the results of your algorithm")
```

### Link your algorithm to encrypt and decrypt buttons
After implementing your algorithm within a method, you must perform the next two steps.

> [!IMPORTANT]
>It is better to include a parameter on each button to know where the call came from (from encrypt button or decrypt button)

```java
// Encrypt button
JButton EncBtn = new JButton("Encrypt");
EncBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	switch (ChooseAlgorithm.getSelectedItem().toString()) {
		case "Example Algorithm": // write here the same name of the algorithm that you wrote in line 74
		// Call here your algorithm function
		break;
		}
	}
});
```
```java
// Decrypt button
JButton DecBtn = new JButton("Decrypt");
DecBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	switch (ChooseAlgorithm.getSelectedItem().toString()) {
		case "Example Algorithm": // write here the same name of the algorithm that you wrote in line 74
		// Call here your algorithm function
	break;
		}
	}
});
```

### HMAC - Digital Signature - Hash
```Java
// HMAC Button
JButton HmacBtn = new JButton("HMAC");
HmacBtn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	// Here implement HMAC Algorithm
	}
});
```
```Java
// Digital Signature Button
JButton DSBtn = new JButton("Digital Signature");
DSBtn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	// Here implement the Digital Signature Algorithm
	}
});
```
```Java
// Hash Button
JButton HashBtn = new JButton("HASH");
HashBtn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	// Here Implement the Hash algorithm
	}
});
```


## Upload Project Guide
1. You Should push your code on `GitHub` using `git`
2. then make a `pull request` to get your code solution as the figure
   
![Screenshot 2023-11-13 230808](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/362e7c00-3410-4748-b641-8bbe55b53eb3)

![Screenshot 2023-11-13 231139](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/14f794c5-0823-4bcb-a516-7d02c5aca0a9)

![Screenshot 2023-11-13 231159](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/16339e3c-84c5-4418-a43c-cc06aa139774)

![Screenshot 2023-11-13 231434](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/51021b4b-833a-4acf-8be3-46e560220f56)


## Community support
Thanks to all the contributors who uploaded the algorithms.
- SHA-1 Algorithm
  by [fahd192](https://github.com/fahd192)

- HMAC Algorithm
   by [AbdulrahmanAlwazzan](https://github.com/AbdulrahmanAlwazzan)

- PlayFair Algorithm

- Homophone Algorithm
  	by [Fahad2001422](https://github.com/Fahad2001422)
  
- RSA Algorithm
  by [AbdullahSZH](https://github.com/AbdullahSZH)
## Team Members
- [Nawaf Al-Noweisri](https://github.com/Noweisri) 
- [Abdullah Al-Saab](https://github.com/amalsaab) 
- [Abdullah Al-Fawaz](https://github.com/AboFawaz0)


