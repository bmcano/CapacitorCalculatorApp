<p>
  <img src="https://github.com/BranCann15/CapacitorCalculatorApp/blob/main/app/src/main/ic_feature_graphic-playstore.webp" title="feature graphic" alt="feature graphic">
</p>

This app is a simple calculator and converter for a standard ceramic capacitor. 
A standard code is 3 digits longs but they are instances where either a 2 or 1 digit code could appear but they just have a defaulted multiplier of 1.

For a 3 digit code `xyz` the first 2 digits `xy` correspond directly to the first 2 digits of the capacitance value in pico-farads and the 3 digit `z` is the multiplier ranging from 0-6. 
The value of the multiplier determines how many zeros are added to the pico-farad value.

This app will then also convert the values to nano-farads and micro-farads. On top of that the user can also enter in a capacitance value to auto-convert the to the others and to get the code for a specific valid capacitance.
