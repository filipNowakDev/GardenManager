import React from "react";
import { Form, FloatingLabel } from "react-bootstrap";
import { useField } from "formik";

export const TextInput = ({ label, ...props }) => {
  const [field, meta] = useField(props);

  return (
    <FloatingLabel label={label}>
      <Form.Control
        placeholder="placeholder"
        isInvalid={meta.touched && meta.error}
        {...field}
        {...props}
      />
      <Form.Control.Feedback type="invalid">{meta.error}</Form.Control.Feedback>
    </FloatingLabel>
  );
};

export const SelectInput = ({ label, ...props }) => {
  const [field, meta] = useField(props);

  return (
    <FloatingLabel label="Device type:">
      <Form.Select
        placeholder="placeholder"
        isInvalid={meta.touched && meta.error}
        {...field}
        {...props}
      />
      <Form.Control.Feedback type="invalid">{meta.error}</Form.Control.Feedback>
    </FloatingLabel>
  );
};
