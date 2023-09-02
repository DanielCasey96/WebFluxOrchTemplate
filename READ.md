example

Bugs

If your url is expecting a idValue of 00x the 0's will be removed, this is due to the controller converting the idValue to String hence removing the seemingly unneeded 0's.
