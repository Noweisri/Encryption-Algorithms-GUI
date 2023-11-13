# Encryption Algorithms GUI <sub>CSC429 - Computer Security Project</sub>
## Table OF Contents
- [Introduction ](#Introduction)
- [Importance of the Platform](#Importance-of-the-Platform)
- [Interface Overview](#Interface-OverView)
- [Integration Guide](#Integration-Guide)
- [Tips for Editing Code](#Tips-for-Editng-code)
 	- [How to add your algorithm to the drop-menu list](#How-to-add-your-algorithm-to-the-drop-menu-list)
   	- [How to get key values](How-to-get-key-values)
  	- [Displaying error](Displaying-error)
  	- [Link your algorithm to encrypt & decrypt buttons](Link-your-algorithm-to-encrypt-&-decrypt-buttons)
)
## Introduction
In an era dominated by digital communication and sensitive data exchange, the significance of robust cryptographic solutions cannot be overstated. 
As the demand for secure information transfer grows, the need for a unified platform integrating various cryptographic algorithms becomes increasingly paramount. This Repository introduces a groundbreaking project – a Graphic User Interface (GUI) platform built through a Java application using the WindowBuilder tool 
 that amalgamates diverse cryptographic algorithms such as RSA, AES, and other related operations, implemented by contributors, contributers can contribute by uploading their own encryption algorithms through GitHub. 


## Importance of the Platform:

- Versatility and Interoperability:
    Our platform addresses the challenge of managing multiple cryptographic algorithms by providing a centralized interface, allowing users to seamlessly navigate through various protocols. This versatility enhances interoperability across different systems and applications.

- Security Enhancement:
   By consolidating a spectrum of cryptographic algorithms, the platform empowers users with the ability to select and combine protocols strategically.
  This not only fortifies the security of sensitive data but also allows for the adaptation of encryption strategies based on specific use cases and evolving       
  threats.

- User-Friendly Experience:
    The GUI design of the platform ensures that even users with limited cryptographic expertise can harness the power of advanced algorithms effortlessly.
  This democratization of cryptographic tools fosters wider adoption and promotes secure practices across diverse user profiles.
  
- Efficiency and Resource Optimization:
    Streamlining cryptographic operations through a unified platform enhances computational efficiency and resource optimization.
  Users can select algorithms based on  performance requirements, striking a balance between security and system resources.

 
## Tools used
  - Java programming language
  - Window builder <sub> Java GUI designer tool that allows you to create Java GUI applications without writing any code </sub>
  - Eclipse IDE

## Interface Overview

Our Graphic User Interface (GUI) has been meticulously crafted to provide users with an effortless and comprehensive experience in managing cryptographic operations. The platform is structured around a straightforward process, offering dedicated spaces for encryption and decryption, algorithm selection, key management, and result handling.


1. Encryption and Decryption Zones:
   
	  The interface features distinct sections for encryption and decryption, ensuring clarity of user intentions.
	Users can seamlessly switch between encryption and decryption modes, streamlining the process based on their specific requirements.

2. Algorithm Selection:
   
	  A user-friendly dropdown menu allows users to choose from a variety of cryptographic algorithms, including RSA, AES, and others.
	This empowers users to tailor their security measures according to the specific needs of their data, whether it be asymmetric encryption with RSA or symmetric encryption with AES.

4. Key Management:
   
	  Recognizing the diverse requirements of users, our GUI allows for the input of multiple keys, facilitating compatibility with various cryptographic algorithms.
	Dedicated buttons for adding, managing, and removing keys ensure flexibility and adaptability to different encryption scenarios.

4. Result Display:
   
	  A visually intuitive results box provides users with real-time feedback on their cryptographic operations.
	Whether encrypting or decrypting, users can instantly view and assess the outcome, promoting transparency and confidence in the chosen algorithms and keys.

5. File Saving Functionality:
   
	  The platform features a dedicated Save File button, allowing users to preserve their encrypted or decrypted data effortlessly.
	This streamlined file-saving process enhances the user's ability to secure and manage their cryptographic results seamlessly.

6. Efficient Workflow:
   
	  Users progress through the encryption or decryption process with a logical and step-by-step workflow, minimizing the potential for errors.
	The GUI design ensures that users, regardless of their cryptographic expertise, can navigate through the platform intuitively and perform complex operations with ease.

## Integration Guide:
Here We'll Speak About How To get your Own Copy of the Code
1. You must have a Github Account 

2. Go to the top right and Click Fork Button
![Screenshot 2023-11-13 215108](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/78968f87-2215-437d-9c4c-e498f51f1512)

3. Make sure that the "Copy the main branch only" button is selected, then Click the Create Fork Button 
![Screenshot 2023-11-13 215238](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/95296f97-e8d6-4378-ad1c-092a48c7b407)

4. Now you have your Own Version Of the Code
![Screenshot 2023-11-13 215404](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/8a02d105-a223-4ae5-aed9-20798f2576e6)
![Screenshot 2023-11-13 220133](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/8b1d129a-38df-4244-babe-74936706f14a)
![Screenshot 2023-11-13 220147](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/142e6576-5cff-4489-a30c-42ee61b6d4ad)
![Screenshot 2023-11-13 220236](https://github.com/Noweisri/Encryption-Algorithms-GUI/assets/103143696/53acc808-415b-422f-9b67-79544a0f1dfd)

5. Add your Changes To the Code ( To Know How to Add your Algorithm Click [Tips for editing code](#Tips-for-editing-code) )

## Tips for editing code:

### How to add your algorithm to the drop-menu list 
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

### Displaying error
try to show errors in all the scenarios, when the user types the Text/key in the wrong format, or when something unpredictable happened, and so on.
```java
Error("Add here the text of your error")
```

### Link your algorithm to encrypt & decrypt buttons
After implementing your algorithm in a separate method, you must perform the next two steps

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


## Group Members
- Nawaf Al-Noweisri
- Abdullah Al-Saab
- Abdullah Al-Fawaz


