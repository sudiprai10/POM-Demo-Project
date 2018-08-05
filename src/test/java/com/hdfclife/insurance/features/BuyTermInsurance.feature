Feature: Buy hdfclife term insurance

  Scenario: Buy 3DLife hdfclife insurance
    Given open browser and get hdfc url to buy
    Given Switch to basic detail form "Nitin","Tiwari","9685968596","nitin.tiwari@gmail.com","Mumbai","14-Sep-1991"
    When choose 3d hdfclife insurance term plan
    Then complete another details "Single","B E","Ghazipur","Laabh Enclave","Street 20","Near Sidhdhivinayak Temple","421301",,"Student","BVNPP1919C"
    Then fill detail form "Neeraj","Tiwari","13/11/2003","Single","Brother","100","85655896633963","Raman","Shalini"
    Then verify confirm page "Confirm Proposal"
