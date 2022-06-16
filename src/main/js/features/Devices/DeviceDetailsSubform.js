import React, { useState } from "react";

const formTypes = ["", "MQTT", "HTTP"];
const dropDownItems = [
  { label: "Select the device type.", value: "" },
  { label: "MQTT", value: "MQTT" },
  { label: "HTTP", value: "HTTP" },
];
const formFields = {
  "": [],
  MQTT: ["brokerId", "topic"],
  HTTP: ["URL"],
};

export function DeviceDetailsSubform() {
  const [formType, setFormType] = useState(formTypes[0]);

  const handleFormTypeChange = (type) => {
    const fields = formFields[type];
    setFormType(type);

    const formValues = fields.reduce(
      (obj, item) => ({
        ...obj,
        [item]: values.subForm[item] ? values.subForm[item] : "",
      }),
      {}
    );

    const formSchema = fields.reduce(
      (obj, item) => ({
        ...obj,
        [item]: Yup.string().required("Required"),
      }),
      {}
    );

    onChangeForm({
      values: {
        subForm: formValues,
      },
      validation: {
        subForm: Yup.object(formSchema),
      },
    });
    setValues({ ...values, subForm: formValues });
  };

  React.useEffect(() => {
    // Set up the initial values and validation schema on first render
    handleFormTypeChange(formType);
  }, []);

  return (
    <section>
      <Row className="mb-3">
        <Form.Group className="mb-3">
          <SelectInput
            label="Device type"
            name="type"
            onValueChange={(value) => handleFormChange(value)}
          >
            {dropDownItems.map((item) => (
              <option value={item.value}>{item.label}</option>
            ))}
          </SelectInput>
        </Form.Group>
      </Row>
      {!!formType &&
        formFields[formType].map((field) => (
          <Row className="mb-3">
            <Form.Group className="mb-3">
              <TextInput label={field} name={field} type="text" />
            </Form.Group>
          </Row>
        ))}
    </section>
  );
}
