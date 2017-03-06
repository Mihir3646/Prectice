package com.mihir.practice;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TabSongs extends Activity {
    private EditText et_search = null;
    private ListView lv_list = null;
    ArrayAdapter<String> adapter;

    // Listview Data
    String products[] = {"East Timor", "Ecuador", "Egypt", "El Salvador",
            "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
            "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda",
            "Argentina", "Armenia", "Aruba", "Australia", "Austria",
            "Azerbaijan", "Bahrain", "Bangladesh", "Barbados", "Belarus",
            "Belgium", "Monaco", "Mongolia", "Montserrat", "Morocco",
            "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
            "Netherlands", "Netherlands Antilles", "New Caledonia",
            "New Zealand", "Guyana", "Haiti",
            "Heard Island and McDonald Islands", "Honduras", "Hong Kong",
            "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
            "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
            "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
            "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Nicaragua", "Niger",
            "Nigeria", "Niue", "Norfolk Island", "North Korea",
            "Northern Marianas", "Norway", "Oman", "Pakistan", "Palau",
            "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
            "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
            "French Southern Territories", "Gabon", "Georgia", "Germany",
            "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada",
            "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
            "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
            "Micronesia", "Moldova", "Bosnia and Herzegovina", "Botswana",
            "Bouvet Island", "Brazil", "British Indian Ocean Territory",
            "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone",
            "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
            "South Africa", "South Georgia and the South Sandwich Islands",
            "South Korea", "Spain", "Sri Lanka", "Sudan", "Suriname",
            "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland",
            "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
            "The Bahamas", "The Gambia", "Togo", "Tokelau", "Tonga",
            "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
            "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States",
            "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
            "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
            "Virgin Islands", "Wallis and Futuna", "Western Sahara",
            "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso",
            "Burundi", "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada",
            "Cape Verde", "Cayman Islands", "Central African Republic", "Chad",
            "Chile", "China", "Reunion", "Romania", "Russia", "Rwanda",
            "Sqo Tome and Principe", "Saint Helena", "Saint Kitts and Nevis",
            "Saint Lucia", "Saint Pierre and Miquelon", "Belize", "Benin",
            "Bermuda", "Bhutan", "Bolivia", "Christmas Island",
            "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
            "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus",
            "Czech Republic", "Democratic Republic of the Congo", "Denmark",
            "Djibouti", "Dominica", "Dominican Republic",
            "Former Yugoslav Republic of Macedonia", "France", "French Guiana",
            "French Polynesia", "Macau", "Madagascar", "Malawi", "Malaysia",
            "Maldives", "Mali", "Malta", "Marshall Islands", "Yemen",
            "Yugoslavia", "Zambia", "Zimbabwe"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_songs);

        lv_list = (ListView) findViewById(R.id.lv_list);
        et_search = (EditText) findViewById(R.id.et_search);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.song_list, R.id.tv_song_name, products);
        lv_list.setAdapter(adapter);

        /**
         * Enabling Search Filter
         **/
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                TabSongs.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }
}
