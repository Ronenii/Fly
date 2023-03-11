# Fly
A social network by Group C


## Naming Conventions:

### Object naming
- CONSTANTS are all caps.
- Acronyms should only capitalize the first letter. For example, functionUrl and unitId. Not unitID.
- Prefix with the type of object. For example a TextView which contains a name would be tvName. An EditView with a password would be etPass.
- If it's something usually used only once in an activity (e.g. ListView), don't be afraid to just call it lv.
- If it's not an object type just name it by it's function. For example, if it's a string that holds the ID, name it as id, not stringId. The IDE will tell you when it's a string or a float or a long.
- Keep it legible. Use something like Pass instead of Password.
- Within the XML, name should be underscore with no capitals, e.g. tv_name and et_pass
- Put the android:id as the first attribute in the XML.

### File naming
- Prefix layouts with the type it is. E.g. fragment_contact_details.xml, view_primary_button.xml, activity_main.xml.
- For the classes, categorize them into folders, but use suffixes. For example, /activities/MainActivity.java or /fragments/DeleteDialog.java. (example for folders are activities, fragments, adapters, models, and utils)
- Adapters should say how and when they are used. So a ListView adapter for ChatActivity might be called ChatListAdapter.

### colors.xml and dimens.xml as a pallete
- For color, use names like gray_light, not button_foreground.
- For dimens, use names like spacing_large, not button_upper_padding.
- If you want to set something specific for your button color or padding, use a style file.

### strings.xml
Name your strings with keys that resemble namespaces, and don't be afraid of repeating a value for two or more keys.
Use error.message.network, not network_error.

### Short names of major components:
* Button - btn
* EditText - et
* TextView - tv
* ProgressBar - pb
* Checkbox - chk
* RadioButton - rb
* ToggleButton - tb
* Spinner - spn
* Menu - mnu
* ListView - lv
* GalleryView - gv
* LinearLayout -ll
* RelativeLayout - rl
