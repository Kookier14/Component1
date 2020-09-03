

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from TodayIDL.idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

#ifndef NDDS_STANDALONE_TYPE
#ifndef ndds_cpp_h
#include "ndds/ndds_cpp.h"
#endif
#ifndef dds_c_log_impl_h              
#include "dds_c/dds_c_log_impl.h"                                
#endif        

#ifndef cdr_type_h
#include "cdr/cdr_type.h"
#endif    

#ifndef osapi_heap_h
#include "osapi/osapi_heap.h" 
#endif
#else
#include "ndds_standalone_type.h"
#endif

#include "TodayIDL.h"

/* ========================================================================= */
const char *PostDataTYPENAME = "PostData";

DDS_TypeCode* PostData_get_typecode()
{
    static RTIBool is_initialized = RTI_FALSE;

    static DDS_TypeCode PostData_g_tc_x_string = DDS_INITIALIZE_STRING_TYPECODE((255));
    static DDS_TypeCode_Member PostData_g_tc_members[1]=
    {

        {
            (char *)"x",/* Member name */
            {
                0,/* Representation ID */          
                DDS_BOOLEAN_FALSE,/* Is a pointer? */
                -1, /* Bitfield bits */
                NULL/* Member type code is assigned later */
            },
            0, /* Ignored */
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            RTI_CDR_REQUIRED_MEMBER, /* Is a key? */
            DDS_PUBLIC_MEMBER,/* Member visibility */
            1,
            NULL/* Ignored */
        }
    };

    static DDS_TypeCode PostData_g_tc =
    {{
            DDS_TK_STRUCT,/* Kind */
            DDS_BOOLEAN_FALSE, /* Ignored */
            -1, /*Ignored*/
            (char *)"PostData", /* Name */
            NULL, /* Ignored */      
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            1, /* Number of members */
            PostData_g_tc_members, /* Members */
            DDS_VM_NONE  /* Ignored */         
        }}; /* Type code for PostData*/

    if (is_initialized) {
        return &PostData_g_tc;
    }

    PostData_g_tc_members[0]._representation._typeCode = (RTICdrTypeCode *)&PostData_g_tc_x_string;

    is_initialized = RTI_TRUE;

    return &PostData_g_tc;
}

RTIBool PostData_initialize(
    PostData* sample) {
    return PostData_initialize_ex(sample,RTI_TRUE,RTI_TRUE);
}

RTIBool PostData_initialize_ex(
    PostData* sample,RTIBool allocatePointers, RTIBool allocateMemory)
{

    struct DDS_TypeAllocationParams_t allocParams =
    DDS_TYPE_ALLOCATION_PARAMS_DEFAULT;

    allocParams.allocate_pointers =  (DDS_Boolean)allocatePointers;
    allocParams.allocate_memory = (DDS_Boolean)allocateMemory;

    return PostData_initialize_w_params(
        sample,&allocParams);

}

RTIBool PostData_initialize_w_params(
    PostData* sample, const struct DDS_TypeAllocationParams_t * allocParams)
{

    if (allocParams) {} /* To avoid warnings */

    if (allocParams->allocate_memory){
        sample->x= DDS_String_alloc ((255));
        if (sample->x == NULL) {
            return RTI_FALSE;
        }

    } else {
        if (sample->x!= NULL) { 
            sample->x[0] = '\0';
        }
    }

    return RTI_TRUE;
}

void PostData_finalize(
    PostData* sample)
{

    PostData_finalize_ex(sample,RTI_TRUE);
}

void PostData_finalize_ex(
    PostData* sample,RTIBool deletePointers)
{
    struct DDS_TypeDeallocationParams_t deallocParams =
    DDS_TYPE_DEALLOCATION_PARAMS_DEFAULT;

    if (sample==NULL) {
        return;
    } 

    deallocParams.delete_pointers = (DDS_Boolean)deletePointers;

    PostData_finalize_w_params(
        sample,&deallocParams);
}

void PostData_finalize_w_params(
    PostData* sample,const struct DDS_TypeDeallocationParams_t * deallocParams)
{

    if (sample==NULL) {
        return;
    }
    if (deallocParams) {} /* To avoid warnings */

    if (sample->x != NULL) {
        DDS_String_free(sample->x);
        sample->x=NULL;

    }
}

void PostData_finalize_optional_members(
    PostData* sample, RTIBool deletePointers)
{
    struct DDS_TypeDeallocationParams_t deallocParamsTmp =
    DDS_TYPE_DEALLOCATION_PARAMS_DEFAULT;
    struct DDS_TypeDeallocationParams_t * deallocParams =
    &deallocParamsTmp;

    if (sample==NULL) {
        return;
    } 
    if (deallocParams) {} /* To avoid warnings */

    deallocParamsTmp.delete_pointers = (DDS_Boolean)deletePointers;
    deallocParamsTmp.delete_optional_members = DDS_BOOLEAN_TRUE;

}

RTIBool PostData_copy(
    PostData* dst,
    const PostData* src)
{

    if (!RTICdrType_copyStringEx (
        &dst->x, src->x, 
        (255) + 1, RTI_FALSE)){
        return RTI_FALSE;
    }

    return RTI_TRUE;
}

/**
* <<IMPLEMENTATION>>
*
* Defines:  TSeq, T
*
* Configure and implement 'PostData' sequence class.
*/
#define T PostData
#define TSeq PostDataSeq
#define T_initialize_w_params PostData_initialize_w_params
#define T_finalize_w_params   PostData_finalize_w_params
#define T_copy       PostData_copy

#ifndef NDDS_STANDALONE_TYPE
#include "dds_c/generic/dds_c_sequence_TSeq.gen"
#include "dds_cpp/generic/dds_cpp_sequence_TSeq.gen"
#else
#include "dds_c_sequence_TSeq.gen"
#include "dds_cpp_sequence_TSeq.gen"
#endif

#undef T_copy
#undef T_finalize_w_params
#undef T_initialize_w_params
#undef TSeq
#undef T

/* ========================================================================= */
const char *uiadd_retTYPENAME = "uiadd_ret";

DDS_TypeCode* uiadd_ret_get_typecode()
{
    static RTIBool is_initialized = RTI_FALSE;

    static DDS_TypeCode_Member uiadd_ret_g_tc_members[2]=
    {

        {
            (char *)"result",/* Member name */
            {
                0,/* Representation ID */          
                DDS_BOOLEAN_FALSE,/* Is a pointer? */
                -1, /* Bitfield bits */
                NULL/* Member type code is assigned later */
            },
            0, /* Ignored */
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            RTI_CDR_REQUIRED_MEMBER, /* Is a key? */
            DDS_PUBLIC_MEMBER,/* Member visibility */
            1,
            NULL/* Ignored */
        }, 
        {
            (char *)"a",/* Member name */
            {
                1,/* Representation ID */          
                DDS_BOOLEAN_FALSE,/* Is a pointer? */
                -1, /* Bitfield bits */
                NULL/* Member type code is assigned later */
            },
            0, /* Ignored */
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            RTI_CDR_REQUIRED_MEMBER, /* Is a key? */
            DDS_PUBLIC_MEMBER,/* Member visibility */
            1,
            NULL/* Ignored */
        }
    };

    static DDS_TypeCode uiadd_ret_g_tc =
    {{
            DDS_TK_STRUCT,/* Kind */
            DDS_BOOLEAN_FALSE, /* Ignored */
            -1, /*Ignored*/
            (char *)"uiadd_ret", /* Name */
            NULL, /* Ignored */      
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            2, /* Number of members */
            uiadd_ret_g_tc_members, /* Members */
            DDS_VM_NONE  /* Ignored */         
        }}; /* Type code for uiadd_ret*/

    if (is_initialized) {
        return &uiadd_ret_g_tc;
    }

    uiadd_ret_g_tc_members[0]._representation._typeCode = (RTICdrTypeCode *)&DDS_g_tc_long;

    uiadd_ret_g_tc_members[1]._representation._typeCode = (RTICdrTypeCode *)&DDS_g_tc_long;

    is_initialized = RTI_TRUE;

    return &uiadd_ret_g_tc;
}

RTIBool uiadd_ret_initialize(
    uiadd_ret* sample) {
    return uiadd_ret_initialize_ex(sample,RTI_TRUE,RTI_TRUE);
}

RTIBool uiadd_ret_initialize_ex(
    uiadd_ret* sample,RTIBool allocatePointers, RTIBool allocateMemory)
{

    struct DDS_TypeAllocationParams_t allocParams =
    DDS_TYPE_ALLOCATION_PARAMS_DEFAULT;

    allocParams.allocate_pointers =  (DDS_Boolean)allocatePointers;
    allocParams.allocate_memory = (DDS_Boolean)allocateMemory;

    return uiadd_ret_initialize_w_params(
        sample,&allocParams);

}

RTIBool uiadd_ret_initialize_w_params(
    uiadd_ret* sample, const struct DDS_TypeAllocationParams_t * allocParams)
{

    if (allocParams) {} /* To avoid warnings */

    if (!RTICdrType_initLong(&sample->result)) {
        return RTI_FALSE;
    }     

    if (!RTICdrType_initLong(&sample->a)) {
        return RTI_FALSE;
    }     

    return RTI_TRUE;
}

void uiadd_ret_finalize(
    uiadd_ret* sample)
{

    uiadd_ret_finalize_ex(sample,RTI_TRUE);
}

void uiadd_ret_finalize_ex(
    uiadd_ret* sample,RTIBool deletePointers)
{
    struct DDS_TypeDeallocationParams_t deallocParams =
    DDS_TYPE_DEALLOCATION_PARAMS_DEFAULT;

    if (sample==NULL) {
        return;
    } 

    deallocParams.delete_pointers = (DDS_Boolean)deletePointers;

    uiadd_ret_finalize_w_params(
        sample,&deallocParams);
}

void uiadd_ret_finalize_w_params(
    uiadd_ret* sample,const struct DDS_TypeDeallocationParams_t * deallocParams)
{

    if (sample==NULL) {
        return;
    }
    if (deallocParams) {} /* To avoid warnings */

}

void uiadd_ret_finalize_optional_members(
    uiadd_ret* sample, RTIBool deletePointers)
{
    struct DDS_TypeDeallocationParams_t deallocParamsTmp =
    DDS_TYPE_DEALLOCATION_PARAMS_DEFAULT;
    struct DDS_TypeDeallocationParams_t * deallocParams =
    &deallocParamsTmp;

    if (sample==NULL) {
        return;
    } 
    if (deallocParams) {} /* To avoid warnings */

    deallocParamsTmp.delete_pointers = (DDS_Boolean)deletePointers;
    deallocParamsTmp.delete_optional_members = DDS_BOOLEAN_TRUE;

}

RTIBool uiadd_ret_copy(
    uiadd_ret* dst,
    const uiadd_ret* src)
{

    if (!RTICdrType_copyLong (
        &dst->result, &src->result)) { 
        return RTI_FALSE;
    }
    if (!RTICdrType_copyLong (
        &dst->a, &src->a)) { 
        return RTI_FALSE;
    }

    return RTI_TRUE;
}

/**
* <<IMPLEMENTATION>>
*
* Defines:  TSeq, T
*
* Configure and implement 'uiadd_ret' sequence class.
*/
#define T uiadd_ret
#define TSeq uiadd_retSeq
#define T_initialize_w_params uiadd_ret_initialize_w_params
#define T_finalize_w_params   uiadd_ret_finalize_w_params
#define T_copy       uiadd_ret_copy

#ifndef NDDS_STANDALONE_TYPE
#include "dds_c/generic/dds_c_sequence_TSeq.gen"
#include "dds_cpp/generic/dds_cpp_sequence_TSeq.gen"
#else
#include "dds_c_sequence_TSeq.gen"
#include "dds_cpp_sequence_TSeq.gen"
#endif

#undef T_copy
#undef T_finalize_w_params
#undef T_initialize_w_params
#undef TSeq
#undef T

/* ========================================================================= */
const char *uiadd_paramsTYPENAME = "uiadd_params";

DDS_TypeCode* uiadd_params_get_typecode()
{
    static RTIBool is_initialized = RTI_FALSE;

    static DDS_TypeCode_Member uiadd_params_g_tc_members[2]=
    {

        {
            (char *)"a",/* Member name */
            {
                0,/* Representation ID */          
                DDS_BOOLEAN_FALSE,/* Is a pointer? */
                -1, /* Bitfield bits */
                NULL/* Member type code is assigned later */
            },
            0, /* Ignored */
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            RTI_CDR_REQUIRED_MEMBER, /* Is a key? */
            DDS_PUBLIC_MEMBER,/* Member visibility */
            1,
            NULL/* Ignored */
        }, 
        {
            (char *)"b",/* Member name */
            {
                1,/* Representation ID */          
                DDS_BOOLEAN_FALSE,/* Is a pointer? */
                -1, /* Bitfield bits */
                NULL/* Member type code is assigned later */
            },
            0, /* Ignored */
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            RTI_CDR_REQUIRED_MEMBER, /* Is a key? */
            DDS_PUBLIC_MEMBER,/* Member visibility */
            1,
            NULL/* Ignored */
        }
    };

    static DDS_TypeCode uiadd_params_g_tc =
    {{
            DDS_TK_STRUCT,/* Kind */
            DDS_BOOLEAN_FALSE, /* Ignored */
            -1, /*Ignored*/
            (char *)"uiadd_params", /* Name */
            NULL, /* Ignored */      
            0, /* Ignored */
            0, /* Ignored */
            NULL, /* Ignored */
            2, /* Number of members */
            uiadd_params_g_tc_members, /* Members */
            DDS_VM_NONE  /* Ignored */         
        }}; /* Type code for uiadd_params*/

    if (is_initialized) {
        return &uiadd_params_g_tc;
    }

    uiadd_params_g_tc_members[0]._representation._typeCode = (RTICdrTypeCode *)&DDS_g_tc_long;

    uiadd_params_g_tc_members[1]._representation._typeCode = (RTICdrTypeCode *)&DDS_g_tc_long;

    is_initialized = RTI_TRUE;

    return &uiadd_params_g_tc;
}

RTIBool uiadd_params_initialize(
    uiadd_params* sample) {
    return uiadd_params_initialize_ex(sample,RTI_TRUE,RTI_TRUE);
}

RTIBool uiadd_params_initialize_ex(
    uiadd_params* sample,RTIBool allocatePointers, RTIBool allocateMemory)
{

    struct DDS_TypeAllocationParams_t allocParams =
    DDS_TYPE_ALLOCATION_PARAMS_DEFAULT;

    allocParams.allocate_pointers =  (DDS_Boolean)allocatePointers;
    allocParams.allocate_memory = (DDS_Boolean)allocateMemory;

    return uiadd_params_initialize_w_params(
        sample,&allocParams);

}

RTIBool uiadd_params_initialize_w_params(
    uiadd_params* sample, const struct DDS_TypeAllocationParams_t * allocParams)
{

    if (allocParams) {} /* To avoid warnings */

    if (!RTICdrType_initLong(&sample->a)) {
        return RTI_FALSE;
    }     

    if (!RTICdrType_initLong(&sample->b)) {
        return RTI_FALSE;
    }     

    return RTI_TRUE;
}

void uiadd_params_finalize(
    uiadd_params* sample)
{

    uiadd_params_finalize_ex(sample,RTI_TRUE);
}

void uiadd_params_finalize_ex(
    uiadd_params* sample,RTIBool deletePointers)
{
    struct DDS_TypeDeallocationParams_t deallocParams =
    DDS_TYPE_DEALLOCATION_PARAMS_DEFAULT;

    if (sample==NULL) {
        return;
    } 

    deallocParams.delete_pointers = (DDS_Boolean)deletePointers;

    uiadd_params_finalize_w_params(
        sample,&deallocParams);
}

void uiadd_params_finalize_w_params(
    uiadd_params* sample,const struct DDS_TypeDeallocationParams_t * deallocParams)
{

    if (sample==NULL) {
        return;
    }
    if (deallocParams) {} /* To avoid warnings */

}

void uiadd_params_finalize_optional_members(
    uiadd_params* sample, RTIBool deletePointers)
{
    struct DDS_TypeDeallocationParams_t deallocParamsTmp =
    DDS_TYPE_DEALLOCATION_PARAMS_DEFAULT;
    struct DDS_TypeDeallocationParams_t * deallocParams =
    &deallocParamsTmp;

    if (sample==NULL) {
        return;
    } 
    if (deallocParams) {} /* To avoid warnings */

    deallocParamsTmp.delete_pointers = (DDS_Boolean)deletePointers;
    deallocParamsTmp.delete_optional_members = DDS_BOOLEAN_TRUE;

}

RTIBool uiadd_params_copy(
    uiadd_params* dst,
    const uiadd_params* src)
{

    if (!RTICdrType_copyLong (
        &dst->a, &src->a)) { 
        return RTI_FALSE;
    }
    if (!RTICdrType_copyLong (
        &dst->b, &src->b)) { 
        return RTI_FALSE;
    }

    return RTI_TRUE;
}

/**
* <<IMPLEMENTATION>>
*
* Defines:  TSeq, T
*
* Configure and implement 'uiadd_params' sequence class.
*/
#define T uiadd_params
#define TSeq uiadd_paramsSeq
#define T_initialize_w_params uiadd_params_initialize_w_params
#define T_finalize_w_params   uiadd_params_finalize_w_params
#define T_copy       uiadd_params_copy

#ifndef NDDS_STANDALONE_TYPE
#include "dds_c/generic/dds_c_sequence_TSeq.gen"
#include "dds_cpp/generic/dds_cpp_sequence_TSeq.gen"
#else
#include "dds_c_sequence_TSeq.gen"
#include "dds_cpp_sequence_TSeq.gen"
#endif

#undef T_copy
#undef T_finalize_w_params
#undef T_initialize_w_params
#undef TSeq
#undef T

