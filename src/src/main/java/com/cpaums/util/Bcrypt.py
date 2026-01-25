import bcrypt

password = 'user1'
salt = bcrypt.gensalt()
new_hash = bcrypt.hashpw(password.encode('utf-8'), salt)

print(new_hash.decode('utf-8'))
