import React from 'react';

const UserContext = React.createContext({user: {}});
export const UserProvider = ({children}) => {
    const [user, setUser] = React.useState(null);

    return (
        <UserContext.Provider value={{user, setUser}}>
            {children}
        </UserContext.Provider>
    );
};


export default UserContext;
