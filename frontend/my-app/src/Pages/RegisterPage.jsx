import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import UserForm from "../Components/UserForm.jsx";
import Loading from "../Components/Loading/index.js";
import UserContext from "./UserContext.jsx";
import Header from "../Components/Header.jsx";

const createUser = (user) => {
    return fetch("/api/users/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    });
};

const checkEmail = (email) => {
    const emailObject = {
        email: email
    };
    return fetch("/api/users/checkemail", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(emailObject),
    }).then((res) => res.json());
}

const RegisterPage = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const { user, setUser } = React.useContext(UserContext);

    const handleCreateUser = (user) => {
        setLoading(true);

        createUser(user)
            .then(() => {
                setLoading(false);
                navigate("/");
            });
    };

    const handleCheckEmail = async (email) => {
        try {
            const data = await checkEmail(email);
            console.log(data);
            return data;
        } catch (error) {
            console.error(error);
            return false;
        }
    }

    if (loading) {
        return <Loading/>;
    }

    return (
        <>
        <Header/>
        <UserForm
            onSave={handleCreateUser}
            checkEmail={handleCheckEmail}
            disabled={loading}
            onCancel={() => navigate("/")}
        />
            </>
    )
}

export default RegisterPage;
