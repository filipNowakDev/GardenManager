import { nanoid } from "@reduxjs/toolkit";
import React, { useRef, useState } from "react";
import { Row, Button, FloatingLabel, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { deviceAdded } from "./devicesSlice";
import { Formik, useFormikContext } from "formik";
import * as yup from "yup";
import { TextInput, SelectInput } from "../../app/formComponents";

export function AddDeviceForm() {
  const mainFormInitialValues = {
    name: "",
    subForm: {},
  };

  const dispatch = useDispatch();
  const [initialValues, setInitialValues] = useState(mainFormInitialValues);
  const [validation, setValidation] = useState(formValidation);

  const onSubmit = (values, { setSubmitting }) => {
    dispatch(
      deviceAdded({
        id: nanoid(),
        name: values.name,
        type: values.type,
      })
    );
    setSubmitting(false);
  };

  return (
    <section>
      <h2>Add a new device</h2>
      <Formik
        validationSchema={validation}
        onSubmit={onSubmit}
        innerRef={formRef}
        initialValues={initialValues}
      >
        {({ handleSubmit, values, isValid, isSubmitting }) => {
          let detailsFields = <section></section>;
          if (values.type === "mqtt") {
            if (typeof values.brokerId === "undefined") {
              values.brokerId = "";
            }
            detailsFields = (
              <section>
                <Row className="mb-3">
                  <Form.Group className="mb-3">
                    <TextInput label="Broker ID" name="brokerId" type="text" />
                  </Form.Group>
                </Row>
              </section>
            );
          }
          return (
            <Form noValidate onSubmit={handleSubmit}>
              <Row className="mb-3">
                <Form.Group className="mb-3">
                  <TextInput label="Device name" name="name" type="text" />
                </Form.Group>
              </Row>
              <Row className="mb-3">
                <Form.Group className="mb-3">
                  <SelectInput label="Device type" name="type">
                    <option value="">Select device type...</option>
                    <option value="mqtt">MQTT</option>
                    <option value="http">HTTP</option>
                  </SelectInput>
                </Form.Group>
              </Row>
              {detailsFields}

              <Row className="mb-3">
                <Button
                  variant="primary"
                  type="submit"
                  disabled={!isValid && isSubmitting}
                >
                  Save Device
                </Button>
              </Row>
            </Form>
          );
        }}
      </Formik>
    </section>
  );
}
